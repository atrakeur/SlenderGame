package engine.render;

import engine.math.Vector2;

public class Camera {
	
	private Vector2 position = new Vector2(0,0);
	private Vector2 size = new Vector2(2,2);
	
	
	public Vector2 getPosition() {
		return position;
	}
	public void setPosition(Vector2 position) {
		this.position = position;
	}
	public Vector2 getSize() {
		return size;
	}
	public void setSize(Vector2 size) {
		this.size = size;
	}

}
