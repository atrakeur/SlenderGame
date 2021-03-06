package engine.world.tile;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

/**
 * A random tile that show with a random color for testing purpose
 * 
 * @author Valentin 'Atrakeur' Letourneur <atrakeur@gmail.com>
 * Created 8 mars 2013 at 18:42:32
 *
 */
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

	public boolean isBlocked() {
		return false;
	}

	public Tile copy() {
		return new RandomTile();
	}

}
