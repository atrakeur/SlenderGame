package engine.world.tile;

import java.io.IOException;

import org.lwjgl.opengl.GL11;

import engine.textures.TextureLoader;

public class ImageTile extends Tile {
	
	private String image;
	private boolean isBlocked;

	/**
	 * @param image the image to use for this tile
	 * @param isBlocked if this tile is blocking or not
	 */
	public ImageTile(String image, boolean isBlocked) {
		this.image = image;
		this.isBlocked = isBlocked;
	}

	public Tile copy() {
		return new ImageTile(image, isBlocked);
	}

	public boolean isBlocked() {
		return isBlocked;
	}

	public void onDraw() throws IOException{
		GL11.glColor3f(1f,1f,1f);
		TextureLoader.getTexture(image).bind();
		
		GL11.glBegin(GL11.GL_QUADS);
		
		GL11.glTexCoord2f(0f, 0f);
		GL11.glVertex2f(	-0.5f	,	-0.5f	);
		
		GL11.glTexCoord2f(1f, 0f);
		GL11.glVertex2f(	0.5f	,	-0.5f	);
		
		GL11.glTexCoord2f(1f, 1f);
		GL11.glVertex2f(	0.5f	,	0.5f	);
		
		GL11.glTexCoord2f(0f, 1f);
		GL11.glVertex2f(	-0.5f	,	0.5f	);
		
		GL11.glEnd();
		
		TextureLoader.getTexture(image).unbind();
	}

	public String toString() {
		return "ImageTile [image=" + image + ", isBlocked=" + isBlocked + "]";
	}

}
