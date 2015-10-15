package engine.gui;

import java.awt.Rectangle;

import engine.time.Time;
import org.lwjgl.opengl.GL11;

import engine.input.Input;
import engine.render.Render;
import engine.textures.Texture;

/**
 * Manages all 2D elements used as GUI
 * 
 * @author Valentin 'Atrakeur' Letourneur <atrakeur@gmail.com>
 * Created 8 mars 2013 at 18:16:42
 *
 */
public class GUI {

	public static final float thresold = 10000;
	private static float nextClick = 0;
	
	/**
	 * Print a label to the screen
	 * @param rect the destination rectangle in screen coordinates
	 * @param texture the texture to show up at this position
	 */
	public static void label(Rectangle rect, Texture texture){
		texture.bind();
		
		GL11.glBegin(GL11.GL_QUADS);
		
		GL11.glTexCoord2f(0f, 0f);
		GL11.glVertex2f(	rect.x				,	rect.y				);
		
		GL11.glTexCoord2f(1f, 0f);
		GL11.glVertex2f(	rect.x+rect.width	,	rect.y				);
		
		GL11.glTexCoord2f(1f, 1f);
		GL11.glVertex2f(	rect.x+rect.width	,	rect.y+rect.height	);
		
		GL11.glTexCoord2f(0f, 1f);
		GL11.glVertex2f(	rect.x				,	rect.y+rect.height	);
		
		GL11.glEnd();
		
		texture.unbind();
	}
	
	/**
	 * Print a button on screen
	 * @param rect the destination rectangle
	 * @param texture the texture to draw
	 * @return if the button was pressed last frame or not
	 */
	public static boolean button(Rectangle rect, Texture texture){
		label(rect, texture);

		if (nextClick <= Time.time()) {
			nextClick = Time.time() + thresold;
			return Input.isButtonDown(0) && rect.contains(Input.mousePosition.x, Render.height() - Input.mousePosition.y);
		} else {
			return false;
		}
	}

}
