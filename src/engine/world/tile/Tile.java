package engine.world.tile;

public abstract class Tile {
	
	public abstract void onDraw();
	
	public abstract boolean isBlocked();
	
	public abstract Tile copy(); 

}
