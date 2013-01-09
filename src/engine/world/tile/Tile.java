package engine.world.tile;

import java.io.IOException;

public abstract class Tile {
	
	public abstract void onDraw() throws Exception;
	
	public abstract boolean isBlocked();
	
	public abstract Tile copy();

	public String toString() {
		return "Tile";
	} 

}
