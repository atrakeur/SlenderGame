package engine.entity;

import org.lwjgl.opengl.GL11;

import engine.math.Vector2;

public abstract class Entity implements IEntitable{
	
	private String name = "Entity";
	
	private final Vector2 position = new Vector2();
	private final Vector2 size = new Vector2(1, 1);
	
	public void onCreate(){
	}
	
	public void onDestroy(){
	}

	public String getName() {	return name;	}

	public void setName(String name) {	this.name = name;	}
	
	public Vector2 position(){	return position;	}
	
	public Vector2 size()	 {	return size;	}

}
