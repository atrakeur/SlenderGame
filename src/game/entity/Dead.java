package game.entity;

import java.io.IOException;

import org.lwjgl.opengl.GL11;

import engine.entity.Entity;
import engine.entity.IDrawable;
import engine.textures.TextureLoader;

public class Dead extends Entity implements IDrawable{
	
	public void onStart() throws IOException{
		this.setName("Dead");
	}
	
	public void onDraw() throws IOException{
		GL11.glColor3f(1f,1f,1f);
		TextureLoader.getTexture("dead.png").bind();
		
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
		
		TextureLoader.getTexture("dead.png").unbind();
	}

}
