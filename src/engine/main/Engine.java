package engine.main;

import engine.render.Render;
import engine.world.World;


public abstract class Engine {
	
	private boolean run = true;
	
	/**
	 * Contains Engine initialization, main loop, and engine cleanup
	 */
	public Engine(){
		//Init render, world, input, then callback
		try {
			World.init();
			Render.init();
			
			onInit();
		} catch (Exception e) {
			e.printStackTrace();
			run = false;
		}
		
		while(run){
			//update and draw
			World.update();
			Render.update();
			try {
				Thread.sleep(50);
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
	public void Quit(boolean quit){
		run = !quit;
	}
	
	/**
	 * Called on engine init (after component init)
	 */
	public abstract void onInit();
	
	/**
	 * Called on engine quit (just before components destroy)
	 */
	public abstract void onQuit();

}
