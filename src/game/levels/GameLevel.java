package game.levels;

import java.awt.Color;

import engine.main.*;
import engine.world.World;
import engine.world.background.ColorBackground;
import game.entity.characters.Slender;
import game.entity.characters.Woman;

public class GameLevel extends Level{

	public GameLevel(String levelName) {
		super(levelName);
	}

	public void onDestroy() {
		// TODO Auto-generated method stub
		
	}

	public void onStart() {
		//Setup the world
		World.getMainCamera().size().set(5, 5);
		World.setBackground(new ColorBackground(new Color(0.1f, 0.4f, 0.1f)));
		World.addEntity(new Slender());
		World.addEntity(new Woman());
	}

	public void onUpdate() {
		// TODO Auto-generated method stub
		
	}

}
