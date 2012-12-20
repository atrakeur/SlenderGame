package engine.render;

import engine.entity.Entity;
import engine.entity.IUpdatable;
import engine.math.Vector2;

public class Camera extends Entity implements IUpdatable{
	
	private final Vector2 position = new Vector2(0,0);
	private final Vector2 size = new Vector2(2,2);
	
	public Vector2 position() {
		return position;
	}
	public Vector2 getSize() {
		return size;
	}
	
	public void onUpdate() {
		position.x += 0.01;
	}

}
