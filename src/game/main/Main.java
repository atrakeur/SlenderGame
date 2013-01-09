package game.main;

import engine.exceptions.GameException;
import engine.main.Engine;
import engine.render.Render;
import game.levels.GameLevel;

public class Main extends Engine{
	
	public void onStart(){
		try {
			Render.setWidth(800);
			Render.setHeight(600);
		} catch (GameException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void onInit(){
		this.loadLevel(new GameLevel("TestLevel"));
	}
	
	@Override
	public void onQuit(){
		
	}

	public static void main(String[] args) {
		new Main();
	}

}
