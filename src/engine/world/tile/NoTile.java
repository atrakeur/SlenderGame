package engine.world.tile;

public class NoTile extends Tile {

	public void onDraw() {
	}

	public boolean isBlocked() {
		return false;
	}

	public Tile copy() {
		return new NoTile();
	}

}
