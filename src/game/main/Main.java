package game.main;
import java.awt.Color;

import engine.main.Engine;
import engine.world.World;
import engine.world.background.ColorBackground;
import game.entity.Slender;

public class Main extends Engine{
	
	@Override
	public void onInit(){
		World.getMainCamera().size().set(5, 5);
		World.setBackground(new ColorBackground(new Color(0.1f, 0.4f, 0.1f)));
		World.addEntity(new Slender());
	}
	
	@Override
	public void onQuit(){
		
	}

	public static void main(String[] args) {
		new Main();
	}

}
