package engine.entity;

import engine.math.Vector2;

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
	 * @return the current rotation value
	 */
	public abstract float rotation();
	
	/**
	 * @param newRotation the new Rotation to set to this entity
	 */
	public abstract void rotation(float newRotation);

	public abstract void move(float x, float y);
}