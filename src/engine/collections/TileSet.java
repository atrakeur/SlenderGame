package engine.collections;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import engine.exceptions.GameException;
import engine.world.tile.Tile;

/**
 * A TileSet is used by layers to associate each tile with a given Character
 * 
 * It's used when layer do loading/saving it's contents to a string
 * 
 * @author Valentin 'Atrakeur' Letourneur <atrakeur@gmail.com>
 * Created 8 janv. 2013 at 17:54:50
 *
 */
public class TileSet extends HashMap<Character, Tile>{

}
