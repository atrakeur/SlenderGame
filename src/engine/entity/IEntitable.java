package engine.entity;

import engine.math.Vector2;

public interface IEntitable {

	public abstract void onCreate();

	public abstract void onDestroy();

	public abstract String getName();

	public abstract void setName(String name);

	public abstract Vector2 position();
	
	public abstract Vector2 size();
	
	public abstract float rotation();
	
	public abstract void rotation(float newRotation);

}