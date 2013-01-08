package game.entity;

import java.io.IOException;

import org.lwjgl.opengl.GL11;

import engine.entity.Entity;
import engine.entity.IDrawable;
import engine.entity.IUpdatable;
import engine.textures.TextureLoader;
import engine.time.Time;

public class Slender extends Entity implements IDrawable, IUpdatable{
	
	public void onStart() throws IOException{
		//TextureLoader.getTexture("grass.jpg");
	}
	
	public void onUpdate(){
		
	}
	
	public void onDraw() throws IOException{
		//GL11.glColor3f(0.5f,0.5f,1.0f);
		
		GL11.glBegin(GL11.GL_QUADS);
		
		TextureLoader.getTexture("grass.jpg").bind();
		
		GL11.glTexCoord2f(0f, 0f);
		GL11.glVertex2f(	-0.5f	,	-0.5f	);
		
		GL11.glTexCoord2f(1f, 0f);
		GL11.glVertex2f(	0.5f	,	-0.5f	);
		
		GL11.glTexCoord2f(1f, 1f);
		GL11.glVertex2f(	0.5f	,	0.5f	);
		
		GL11.glTexCoord2f(0f, 1f);
		GL11.glVertex2f(	-0.5f	,	0.5f	);
		
		GL11.glEnd();
	}

}
