package engine.textures;

import java.awt.image.BufferedImage;

import org.lwjgl.opengl.GL11;

public class Texture {
	
	private final String name;
	private int textureID;
	private final BufferedImage image;

	public Texture(String resourceName, BufferedImage image) {
		name = resourceName;
		this.image = image;
		textureID = GLImporter.loadImage(this.image);
	}
	
	public void close(){
		GLImporter.deleteImage(textureID);
	}
	
	public void bind(){
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
	}
	
	public void unbind(){
		GL11.glDisable(GL11.GL_TEXTURE_2D);
	}

}
