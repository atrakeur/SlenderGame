package engine.world;

import engine.exceptions.GameException;
import engine.math.Vector2;

public class Layer {
	
	public final int index;
	
	private Vector2 size;
	private Tile[][] tiles;
	
	public Layer(int index){
		this.index = index;
		
		setSize(15, 15);
	}
	
	public void setSize(int width, int height){
		size = new Vector2(width, height);
		tiles = new Tile[width][height];
		for(int i = 0; i < width; i++)
			for(int j = 0; j < height; j++)
				tiles[i][j] = new Tile();
	}
	
	public Vector2 getSize(){
		return size;
	}
	
	public boolean exist(int x, int y){
		return x >= 0 && x < size.x && y >= 0 && y < size.y;
	}
	
	public Tile getTile(int x, int y) throws GameException{
		if(!exist(x, y))
			throw new GameException("Tile ("+x+", "+y+") don't exist");
		
		return tiles[x][y];
	}

}
