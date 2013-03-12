package engine.entity;

import engine.math.Vector2;

/**
 * Interface implemented by Entity class
 * 
 * @author Valentin 'Atrakeur' Letourneur <atrakeur@gmail.com>
 * Created 8 mars 2013 at 18:22:38
 *
 */
public interface IEntitable {

	/**
	 * Used for initialisation purposes
	 */
	public abstract void onCreate();

	/**
	 * Used for cleanup purposes
	 */
	public abstract void onDestroy();

	/**
	 * @return the name of the entity
	 */
	public abstract String getName();

	/**
	 * @param name the name to give to this entity
	 */
	public abstract void setName(String name);

	/**
	 * @return the Vector2 used for positionning this entity
	 */
	public abstract Vector2 position();
	
	/**
	 * @return the Vector2 used for sizing this entity
	 */
	public abstract Vector2 size();

	/**
	 * Move an entity by x, y amount using TileCollisionDetection
	 * @param x
	 * @param y
	 */
	public abstract void move(float x, float y);
}