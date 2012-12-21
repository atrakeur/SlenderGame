package engine.main;

import org.lwjgl.opengl.Display;

import engine.input.Input;
import engine.render.Render;
import engine.time.Time;
import engine.world.World;


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
			Time.init();
			World.init();
			Render.init();
			
			onInit();
		} catch (Exception e) {
			e.printStackTrace();
			run = false;
		}
		
		while(run && !Display.isCloseRequested()){
			Time.startFrame();
			
			//update world, level and draw
			Input.update();
			World.update();
			updateLevel();
			Render.update();
			
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
			onQuit();
			
			World.destroy();
			Render.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			run = false;
		}
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

}
