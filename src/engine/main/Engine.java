package engine.main;

import engine.render.Render;
import engine.world.World;


public abstract class Engine {
	
	private boolean run = true;
	
	public Engine(){
		//Init render, world, input
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
		
		//quit
		try {
			onQuit();
			
			World.destroy();
			Render.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			run = false;
		}
	}
	
	public void Quit(boolean quit){
		run = !quit;
	}
	
	public abstract void onInit();
	public abstract void onQuit();

}
