package engine.render;

import java.util.Iterator;

import engine.entity.Entity;
import engine.entity.IDrawable;
import engine.entity.IEntitable;
import engine.exceptions.GameException;
import engine.math.Vector2;
import engine.world.World;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class Render {
	
	private static boolean isInit = false;
	
	private static int width = 800;
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
	
	/*
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
	
	/*
	 * Render the game world
	 */
	public static void update(){
		//define new viewport position
		Vector2 pos  = World.getMainCamera().getPosition();
		Vector2 size = World.getMainCamera().getSize();
		
		cameraUpperLeft.x = pos.x - size.x/2;
		cameraUpperLeft.y = pos.y - size.y/2;
		cameraLowerRight.x = pos.x + size.x/2;
		cameraLowerRight.y = pos.y + size.y/2;
		
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(pos.x - size.x/2, pos.y - size.y/2, pos.x + size.x/2, pos.y + size.y/2, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		
		//draw all drawable game objects
		IEntitable e;
		Iterator<Entity> iEnt = World.getEntityIterator();
		while(iEnt.hasNext()){
			e = iEnt.next();
			
			if(!(e instanceof IDrawable))
				continue;
			
			//setup opengl
			
			
				
			((IDrawable)e).onDraw();
			
		}
		
		//update view
		Display.update();
	}
	
	/*
	 * Destroy the Render
	 */
	public static void destroy(){
		Display.destroy();
	}

}
