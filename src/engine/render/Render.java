package engine.render;

import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import engine.entity.Entity;
import engine.entity.IDrawable;
import engine.entity.IEntitable;
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
	
	private Render(){}
	
	/*
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
		Display.setDisplayMode(new DisplayMode(width,height));
		Display.create();
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
		
		updateGUI();
		
		Display.update();
	}
	
	/**
	 * Update viewport calculations
	 */
	public static void updateViewport(){
		Vector2 pos  = World.getMainCamera().position();
		Vector2 size = World.getMainCamera().size();
		
		cameraUpperLeft.x = pos.x - size.x/2;
		cameraUpperLeft.y = pos.y - size.y/2;
		cameraLowerRight.x = pos.x + size.x/2;
		cameraLowerRight.y = pos.y + size.y/2;
		
		ratio = (float)width/(float)height;
	}
	
	/**
	 * Update (draw) background
	 */
	public static void updateBackground(){
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(-0.5f, 0.5f, -0.5f, 0.5f, 1f, -1f);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
		
		World.getBackground().onDraw();
	}
	
	/**
	 * Update (draw) the layer
	 * @param layer to update
	 */
	public static void updateLayer(int layer){	
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(ratio * (cameraUpperLeft.x), ratio * (cameraLowerRight.x), cameraUpperLeft.y, cameraLowerRight.y, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
		
		try {
			Layer l = World.getLayer(layer);
		
			for(int i = 0; i < l.size().x; i++){
				for(int j = 0; j < l.size().y; j++){
					GL11.glLoadIdentity();
					GL11.glTranslatef((-l.size().x+1)/2, (-l.size().y+1)/2, 0);
					GL11.glTranslatef(i, j, 0);
					l.getTile(i, j).onDraw();
				}
			}
		
		} catch (GameException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Update (draw) entities
	 */
	public static void updateEntities(){
		//setup viewport 
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(ratio * (cameraUpperLeft.x), ratio * (cameraLowerRight.x), cameraUpperLeft.y, cameraLowerRight.y, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
		
		//draw all drawable game objects
		List<IDrawable> toDraw = World.getDrawables();
		Collections.sort(toDraw, sorter);
		for(IDrawable e: toDraw){
			
			//setup opengl
			GL11.glLoadIdentity();
			GL11.glTranslatef(e.position().x, e.position().y, 0);
			GL11.glScalef(e.size().x, e.size().y, 0);
			GL11.glRotatef(180, 0, 0, 1);
			GL11.glRotatef(-e.rotation(), 0, 0, 1);
			
			//draw entity
			try {
				e.onDraw();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		}
		
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
	 * Update (draw) GUI
	 */
	public static void updateGUI(){
		//define viewport position
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, width, 0, height, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
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

}
