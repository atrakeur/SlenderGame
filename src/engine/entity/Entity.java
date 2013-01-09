package engine.entity;

import engine.math.Vector2;
import engine.world.World;

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
	private float rotation = 0;
	
	public void onCreate(){
	}
	
	public void onDestroy(){
	}

	public final String getName() {	return name;	}

	public final void setName(String name) {	this.name = name;	}
	
	public final Vector2 position(){	return position;	}
	
	public final Vector2 size()	 {	return size;	}
	
	public final float rotation(){	return rotation;	}
	
	public final void rotation(float rotation){	this.rotation = rotation;	}
	
	public final void move(float x, float y){
		moveX(x);
		moveY(y);
	}

	private final void moveX(float x){
		position.x += x;
		if(World.isPositionBlocked(position.x, position.y))
			position.x -= x;
	}
	
	private void moveY(float y) {
		position.y += y;
		if(World.isPositionBlocked(position.x, position.y))
			position.y -= y;
	}

}
