package game.entity;

import org.lwjgl.opengl.GL11;

import engine.entity.Entity;
import engine.entity.IDrawable;
import engine.entity.IUpdatable;

public class Slender extends Entity implements IDrawable, IUpdatable{
	
	public void onUpdate(){
		rotation(rotation() + 0.1f);
	}
	
	public void onDraw(){
		GL11.glColor3f(0.5f,0.5f,1.0f); 
		
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2f(	-0.5f	,	-0.5f	);
		GL11.glVertex2f(	0.5f	,	-0.5f	);
		GL11.glVertex2f(	0.5f	,	0.5f	);
		GL11.glVertex2f(	-0.5f	,	0.5f	);
		GL11.glEnd();
	}

}
