package engine.main;

import org.lwjgl.opengl.Display;

import engine.debug.Profiler;
import engine.input.Input;
import engine.render.Render;
import engine.resources.Resources;
import engine.time.Time;
import engine.world.World;

/**
 * The main Engine core
 * Your game application must extends this class in order to have access to all other engine features
 * 
 * @author Valentin 'Atrakeur' Letourneur <atrakeur@gmail.com>
 * Created 8 mars 2013 at 18:26:28
 *
 */
public abstract class Engine {
	
	private boolean run = true;
	
	private Level currentLevel;
	private Level nextLevel;
	
	/**
	 * Contains Engine initialization, main loop, and engine cleanup
	 */
	public Engine(){
		//Init render, world, input, then callback
		try {
			Profiler.startProfile("Engine/Start");
			
			onStart();
			
			Time.init();
			Resources.init();
			World.init();
			Render.init();
			
			onInit();
			
			Profiler.endProfile("Engine/Start");
		} catch (Exception e) {
			e.printStackTrace();
			run = false;
		}
		
		while(run && !Display.isCloseRequested()){
			Time.startFrame();
			
			//update world, level
			Profiler.startProfile("Engine/Update");
			Input.update();
			World.update();
			updateLevel();
			Profiler.endProfile("Engine/Update");
			
			//draw world
			Profiler.startProfile("Engine/Render");
			Render.update();
			Profiler.endProfile("Engine/Render");
			
			//Clean world
			World.clean();
			
			//change level if needed
			checkChangeLevel();
			
			//sleep until next frame
			try {
				Thread.sleep(Time.getSleep());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		//call back quit, and destroy
		try {
			Profiler.startProfile("Engine/Quit");
			if(this.currentLevel != null)
				currentLevel.onDestroy();
			
			onQuit();
			
			World.destroy();
			Render.destroy();
			Profiler.endProfile("Engine/Quit");
		} catch (Exception e) {
			e.printStackTrace();
			run = false;
		}
		
		System.out.println(Profiler.output());
	}
	
	/**
	 * @param quit the game or not
	 */
	public void quit(boolean quit){
		run = !quit;
	}
	
	/***************************************
	 * Level part
	 /**************************************/
	
	/**
	 * Unload then load a new level at the end of the frame
	 * @param nextLevel to load
	 */
	public void loadLevel(Level nextLevel){
		this.nextLevel = nextLevel;
	}
	
	/**
	 * Update the current level
	 */
	public void updateLevel(){
		if(currentLevel != null)
			currentLevel.onUpdate();
	}
	
	/**
	 * Check if the level must be changed, and do it
	 */
	private void checkChangeLevel(){
		if(nextLevel != null){
			try {
				if(currentLevel != null)
					currentLevel.onDestroy();
				World.reInit();
				currentLevel = nextLevel;
				currentLevel.onStart();
				nextLevel = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/***************************************
	 * Callback part
	 **************************************/
	
	/**
	 * Called on engine init (after component init)
	 */
	public abstract void onInit();
	
	/**
	 * Called on engine quit (just before components destroy)
	 */
	public abstract void onQuit();
	
	/**
	 * Called just before engine init (so you can set things like resolution)
	 */
	public abstract void onStart();

}
