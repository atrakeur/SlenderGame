package engine.world;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import engine.exceptions.GameException;
import engine.math.Vector2;
import engine.world.tile.NoTile;
import engine.world.tile.Tile;

/**
 * Define a map layer
 * 
 * @author Valentin 'Atrakeur' Letourneur <atrakeur@gmail.com>
 * Created 21 déc. 2012 at 19:32:49
 *
 */
public class Layer {
	
	public final int index;
	
	private Vector2 size;
	
	private Tile[][] tiles;
	
	public Layer(int index){
		this.index = index;
		
		setSize(15, 15);
	}
	
	/**
	 * Define the size of the layer
	 * Clear the tiles
	 * @param width
	 * @param height
	 */
	public void setSize(int width, int height){
		size = new Vector2(width, height);
		tiles = new Tile[width][height];
		
		for(int i = 0; i < width; i++)
			for(int j = 0; j < height; j++)
				tiles[i][j] = new NoTile();
	}
	
	/**
	 * @return the size of the Layer
	 */
	public Vector2 size(){
		return size;
	}
	
	/**
	 * Check if a tile exist or not
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean exist(int x, int y){
		return x >= 0 && x < size.x && y >= 0 && y < size.y;
	}
	
	/**
	 * Return the tile nearest to the given coordinates
	 * @param x
	 * @param y
	 * @return the nearest tile
	 * @throws GameException if tile out of layer
	 */
	public Tile getNearestTile(float x, float y) throws GameException{
		return getTile((int)Math.rint(x), (int)Math.rint(y));
	}
	
	/**
	 * Return the tile at the given coordinates
	 * @param x
	 * @param y
	 * @return the tile at the coordinates
	 * @throws GameException if tile out of layer
	 */
	public Tile getTile(int x, int y) throws GameException{
		if(!exist(x, y))
			throw new GameException("Can't get non existing tile ("+x+","+y+")");
		
		return tiles[x][y];
	}
	
	/**
	 * Redefine the tile at the given coordinates
	 * @param x
	 * @param y
	 * @param tile to set
	 */
	public void setTile(int x, int y, Tile tile){
		if(tile == null)
			tiles[x][y] = new NoTile();
		
		tiles[x][y] = tile;
	}

}
