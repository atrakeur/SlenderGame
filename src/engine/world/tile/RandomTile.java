package engine.world.tile;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

public class RandomTile extends Tile{
	
	private Color color;
	
	public RandomTile(){
		color = new Color((float)Math.random(), (float)Math.random(), (float)Math.random());
	}
	
	public void onDraw(){
		GL11.glColor3f((float)color.getRed()/255,(float)color.getGreen()/255,(float)color.getBlue()/255); 
		
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2f(	-0.5f	,	-0.5f	);
		GL11.glVertex2f(	0.5f	,	-0.5f	);
		GL11.glVertex2f(	0.5f	,	0.5f	);
		GL11.glVertex2f(	-0.5f	,	0.5f	);
		GL11.glEnd();
	}

}
