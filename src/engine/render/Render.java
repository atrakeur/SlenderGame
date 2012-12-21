package engine.render;

import java.util.Iterator;

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
	private static int height = 600;
	
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
		updateViewport();
		
		updateLayer(0);
		
		updateEntities();
		
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
	
	public static void updateLayer(int layer){
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(ratio * (cameraUpperLeft.x), ratio * (cameraLowerRight.x), cameraUpperLeft.y, cameraLowerRight.y, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		
		try {
			Layer l = World.getLayer(layer);
		
			for(int i = 0; i < l.getSize().x; i++){
				for(int j = 0; j < l.getSize().y; j++){
					GL11.glLoadIdentity();
					GL11.glTranslatef(-l.getSize().x/2, -l.getSize().y/2, 0);
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
		
		//draw all drawable game objects
		IEntitable e;
		Iterator<Entity> iEnt = World.getEntityIterator();
		while(iEnt.hasNext()){
			e = iEnt.next();
			
			if(!(e instanceof IDrawable))
				continue;
			
			//setup opengl
			GL11.glLoadIdentity();
			GL11.glTranslatef(e.position().x, e.position().y, 0);
			GL11.glScalef(e.size().x, e.size().y, 0);
			GL11.glRotatef(-e.rotation(), 0, 0, 1);
			
			//draw entity
			((IDrawable)e).onDraw();
			
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
	}
	
	/**
	 * Destroy the Render
	 */
	public static void destroy(){
		Display.destroy();
	}

}
