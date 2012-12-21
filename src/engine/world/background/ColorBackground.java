package engine.world.background;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

public class ColorBackground extends Background {
	
	private Color color;
	
	public ColorBackground(Color c){
		color = c;
	}

	public void onDraw() {
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glColor3f((float)color.getRed()/255,(float)color.getGreen()/255,(float)color.getBlue()/255);
		GL11.glVertex2f(	-0.5f	,	-0.5f	);
		GL11.glVertex2f(	0.5f	,	-0.5f	);
		GL11.glVertex2f(	0.5f	,	0.5f	);
		GL11.glVertex2f(	-0.5f	,	0.5f	);
		GL11.glEnd();
	}

}
