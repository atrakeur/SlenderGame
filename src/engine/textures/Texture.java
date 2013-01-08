package engine.textures;

import org.lwjgl.opengl.GL11;

public class Texture {
	
	private final String name;
	private final int target;
	private final int textureID;
	
	private int width;
	private int height;

	public Texture(String resourceName, int target, int textureID) {
		name = resourceName;
		this.target = target;
		this.textureID = textureID;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public void bind(){
		GL11.glBindTexture(target, textureID);
	}

}
