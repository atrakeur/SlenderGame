package game.main;
import java.awt.Color;

import engine.main.Engine;
import engine.world.World;
import engine.world.background.ColorBackground;
import game.entity.Slender;

public class Main extends Engine{
	
	@Override
	public void onInit(){
		World.getMainCamera().size().set(15, 15);
		//World.setBackground(new ColorBackground(new Color(0.9f, 0.5f, 0.5f)));
		World.addEntity(new Slender());
	}
	
	@Override
	public void onQuit(){
		
	}

	public static void main(String[] args) {
		new Main();
	}

}
