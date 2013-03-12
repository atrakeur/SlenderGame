package engine.world.tile;

/**
 * NoTile is a tile that don't exist
 * This class is used to avoid null pointer exceptions when there is no tile
 * 
 * @author Valentin 'Atrakeur' Letourneur <atrakeur@gmail.com>
 * Created 8 mars 2013 at 18:41:21
 *
 */
public class NoTile extends Tile {

	public void onDraw() {
	}

	public boolean isBlocked() {
		return false;
	}

	public Tile copy() {
		return new NoTile();
	}

	public String toString() {
		return "NoTile";
	}

}
