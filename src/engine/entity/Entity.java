package engine.entity;

import engine.math.Vector2;

/**
 * Entity class
 * 
 * An entity is a movable object in the game World
 * All objects which herit this type can be added to the game World as entity
 * 
 * @author Valentin 'Atrakeur' Letourneur <atrakeur@gmail.com>
 * Created 21 déc. 2012 at 06:41:52
 *
 */
public abstract class Entity implements IEntitable{
	
	private String name = "Entity";
	
	private final Vector2 position = new Vector2();
	private final Vector2 size = new Vector2(1, 1);
	
	public void onCreate(){
	}
	
	public void onDestroy(){
	}

	public final String getName() {	return name;	}

	public final void setName(String name) {	this.name = name;	}
	
	public final Vector2 position(){	return position;	}
	
	public final Vector2 size()	 {	return size;	}

}
