package engine.world.tile;

/**
 * Represent a tile in the engine
 * 
 * A tile is a part of the static gameworld
 * 
 * @author Valentin 'Atrakeur' Letourneur <atrakeur@gmail.com>
 * Created 8 mars 2013 at 18:43:02
 *
 */
public abstract class Tile {
	
	public abstract void onDraw() throws Exception;
	
	public abstract boolean isBlocked();
	
	public abstract Tile copy();

	public String toString() {
		return "Tile";
	} 

}
