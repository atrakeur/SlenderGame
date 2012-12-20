package engine.entity;

import org.lwjgl.opengl.GL11;

import engine.math.Vector2;

public abstract class Entity implements IEntitable{
	
	private String name = "Entity";
	
	private Vector2 position;
	private Vector2 size;
	
	public void onCreate(){
	}
	
	public void onDestroy(){
	}

	public String getName() {	return name;	}

	public void setName(String name) {	this.name = name;	}

}
