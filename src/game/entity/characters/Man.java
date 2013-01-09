package game.entity.characters;

import java.io.IOException;

import org.lwjgl.opengl.GL11;

import engine.textures.TextureLoader;

/**
 * Define a Man character
 * 
 * @author Valentin 'Atrakeur' Letourneur <atrakeur@gmail.com>
 * Created 9 janv. 2013 at 17:18:04
 *
 */
public class Man extends Person{

	public void onDraw() throws IOException {
		GL11.glColor3f(1f,1f,1f);
		TextureLoader.getTexture("man.png").bind();
		
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
		
		TextureLoader.getTexture("man.png").unbind();
	}

}
