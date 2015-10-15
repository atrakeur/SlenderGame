package engine.render;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import engine.debug.Profiler;
import engine.entity.Entity;
import engine.entity.IDrawable;
import engine.entity.IEntitable;
import engine.entity.IGuiable;
import engine.exceptions.GameException;
import engine.math.Vector2;
import engine.world.Layer;
import engine.world.World;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

/**
 * Render class
 * 
 * Static utility that take care of rendering the game world to the screen
 * 
 * @author Valentin 'Atrakeur' Letourneur <atrakeur@gmail.com>
 * Created 21 déc. 2012 at 06:35:01
 *
 */
public class Render {
	
	private static boolean isInit = false;
	
	private static int width = 600;
	private static int height = 400;
	
	private static SortByY sorter = new SortByY();
	
	public static boolean debugAxis = false;
	
	private Render(){}
	
	/**
	 * Init the Render
	 */
	public static void init() throws Exception{
		if(isInit)
			throw new GameException("Can't init Render twice");
		
		initDisplay();
		
		isInit = true;
	}
	
	/**
	 * Init the display
	 */
	private static void initDisplay() throws LWJGLException{
		initLibrary();

		Display.setDisplayMode(new DisplayMode(width, height));
		Display.create();
	}

	private static void initLibrary() {
		String os = System.getProperty("os.name").toLowerCase();
		if (os.contains("win")) {
			System.setProperty("java.library.path", "lib/native/windows");
		}
		else if (os.contains("max")) {
			System.setProperty("java.library.path", "lib/native/macosx");
		}
		else if (os.contains("nix")) {
			System.setProperty("java.library.path", "lib/native/linux");
		}
		else {
			throw new IllegalStateException("Unsupported OS "+os);
		}

		//Reset classloader to force reload of library path
		try {
			Field fieldSysPath = ClassLoader.class.getDeclaredField( "sys_paths" );
			fieldSysPath.setAccessible( true );
			fieldSysPath.set(null, null);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

	}
	
	/*
	 * Static older objects
	 */
	private static final Vector2 cameraUpperLeft = new Vector2();
	private static final Vector2 cameraLowerRight = new Vector2();
	private static float ratio;
	
	/**
	 * Render the game world
	 */
	public static void update(){
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		
		updateViewport();
		
		updateBackground();
		
		updateLayer(0);
		updateLayer(1);
		
		updateEntities();
		
		updateLayer(2);
		
		if(debugAxis)
			updateDebugAxis();
		
		updateGUI();
		
		Display.update();
	}
	
	/**
	 * Update viewport calculations
	 */
	public static void updateViewport(){
		Profiler.startProfile("Engine/Render/Viewport");
		
		Vector2 pos  = World.getMainCamera().position();
		Vector2 size = World.getMainCamera().size();
		
		ratio = (float)width/(float)height;
		
		cameraUpperLeft.x = (pos.x - size.x/2);
		cameraUpperLeft.y = pos.y - size.y/2 / ratio;
		cameraLowerRight.x = (pos.x + size.x/2);
		cameraLowerRight.y = pos.y + size.y/2 / ratio;
		
		Profiler.endProfile("Engine/Render/Viewport");
	}
	
	/**
	 * Update (draw) background
	 */
	public static void updateBackground(){
		Profiler.startProfile("Engine/Render/Background");
		
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(-0.5f, 0.5f, -0.5f, 0.5f, 1f, -1f);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
		
		World.getBackground().onDraw();
		
		Profiler.endProfile("Engine/Render/Background");
	}
	
	/**
	 * Update (draw) the layer
	 * @param layer to update
	 */
	public static void updateLayer(int layer){
		Profiler.startProfile("Engine/Render/Layer"+layer);
		
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(cameraUpperLeft.x, cameraLowerRight.x, cameraUpperLeft.y, cameraLowerRight.y, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
		
		try {
			Layer l = World.getLayer(layer);
		
			for(int i = (int) (-l.size().x/2); i < l.size().x/2; i++){
				for(int j = (int) (-l.size().x/2); j < l.size().y/2; j++){
					GL11.glLoadIdentity();
					GL11.glTranslatef(i, j, 0);
					try {
						l.getTile(i, j).onDraw();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		
		} catch (GameException e) {
			e.printStackTrace();
		}
		
		Profiler.endProfile("Engine/Render/Layer"+layer);
	}
	
	/**
	 * Update (draw) entities
	 */
	public static void updateEntities(){
		Profiler.startProfile("Engine/Render/Entities");
		
		//setup viewport 
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(cameraUpperLeft.x, cameraLowerRight.x, cameraUpperLeft.y, cameraLowerRight.y, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
		
		//draw all drawable game objects
		List<IDrawable> toDraw = World.getDrawables();
		Collections.sort(toDraw, sorter);
		for(IDrawable e: toDraw){
			
			//setup opengl
			GL11.glLoadIdentity();
			GL11.glScalef(e.size().x, e.size().y, 0);
			GL11.glTranslatef(e.position().x, e.position().y, 0);
			GL11.glRotatef(180, 0, 0, 1);
			
			//draw entity
			try {
				e.onDraw();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		}
		
		Profiler.endProfile("Engine/Render/Entities");
		
	}
	
	/**
	 * Update (draw) GUI
	 */
	public static void updateGUI(){
		Profiler.startProfile("Engine/Render/GUI");
		
		//define viewport position
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, width, height, 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
		
		//call onGUI on all GUIables entities
		for(IGuiable g: World.getGuiables())
			g.onGUI();
		
		Profiler.endProfile("Engine/Render/GUI");
	}
	
	public static void updateDebugAxis(){
		//setup viewport 
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(ratio * cameraUpperLeft.x, ratio * cameraLowerRight.x, cameraUpperLeft.y, cameraLowerRight.y, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
		
		//draw debug axis
		GL11.glLoadIdentity();
		GL11.glTranslatef(ratio * World.getMainCamera().position().x, World.getMainCamera().position().y, 0);
		GL11.glBegin(GL11.GL_LINES);
			GL11.glColor3f(1, 0, 0);
			GL11.glVertex3f(0.5f, 0, 0);
			GL11.glVertex3f(0, 0, 0);
			
			GL11.glColor3f(0, 1, 0);
			GL11.glVertex3f(0, 0.5f, 0);
			GL11.glVertex3f(0, 0, 0);
		GL11.glEnd();
	}
	
	/**
	 * Destroy the Render
	 */
	public static void destroy(){
		Display.destroy();
	}
	
	public static int width(){
		return width;
	}
	
	public static void setWidth(int width) throws GameException{
		if(isInit)
			throw new GameException("Can't change game resolution after init Change resolution in onStart instead");
		
		Render.width = width;
	}
	
	public static int height(){
		return height;
	}
	
	public static void setHeight(int height) throws GameException{
		if(isInit)
			throw new GameException("Can't change game resolution after init Change resolution in onStart instead");
		
		Render.height = height;
	}
	
	public static float ratio(){
		return ratio;
	}

}
