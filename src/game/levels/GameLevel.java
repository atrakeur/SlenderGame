package game.levels;

import java.awt.Color;
import java.io.IOException;

import engine.collections.TileSet;
import engine.exceptions.GameException;
import engine.main.*;
import engine.resources.Resources;
import engine.world.World;
import engine.world.background.ColorBackground;
import engine.world.tile.ImageTile;
import engine.world.tile.NoTile;
import game.entity.characters.Man;
import game.entity.Slender;
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
		World.getMainCamera().size().set(15, 15);
		World.setBackground(new ColorBackground(new Color(0.1f, 0.4f, 0.1f)));
		
		//Setup the map
		try {
			
			TileSet layer0Tiles = new TileSet();
			layer0Tiles.put('g', new ImageTile("tiles/bg_grass.png", false));
			layer0Tiles.put('d', new ImageTile("tiles/bg_dirt.png", false));
			layer0Tiles.put('s', new ImageTile("tiles/bg_sand.png", false));
			layer0Tiles.put('S', new ImageTile("tiles/bg_stone.png", true));
			layer0Tiles.put('w', new ImageTile("tiles/bg_wall.png", true));
			layer0Tiles.put('W', new ImageTile("tiles/bg_wall2.png", true));
			layer0Tiles.put('l', new ImageTile("tiles/bg_water.png", true));
			World.getLayer(0).loadFromString(Resources.loadText("levels/"+levelName+"/layer0"), layer0Tiles);
			
			TileSet layer2Tiles = new TileSet();
			layer2Tiles.put('-', new NoTile());
			layer2Tiles.put('T', new ImageTile("tiles/object_tree.png", false));
			World.getLayer(2).loadFromString(Resources.loadText("levels/"+levelName+"/layer2"), layer2Tiles);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//Setup entities
		World.addEntity(new Slender());
		World.addEntity(new Woman());
		World.addEntity(new Woman());
		World.addEntity(new Woman());
		World.addEntity(new Man());
		World.addEntity(new Man());
		World.addEntity(new Man());
	}

	public void onUpdate() {
		// TODO Auto-generated method stub
		
	}

}
