package game.entity.characters;

import java.io.IOException;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import engine.entity.Entity;
import engine.entity.IDrawable;
import engine.entity.IUpdatable;
import engine.input.Input;
import engine.textures.TextureLoader;
import engine.time.Time;
import engine.world.World;

/**
 * Define the Slender character, who is controlled by the player
 * @author Valentin 'Atrakeur' Letourneur <atrakeur@gmail.com>
 * Created 9 janv. 2013 at 17:17:12
 *
 */
public class Slender extends Entity implements IDrawable, IUpdatable{
	
	public void onStart() throws IOException{
		this.setName("Slender");
	}
	
	public void onUpdate(){
		int x = 0;
		int y = 0;
		
		if(Input.isKeyDown(Keyboard.KEY_S))
			y--;
		if(Input.isKeyDown(Keyboard.KEY_Z))
			y++;
		if(Input.isKeyDown(Keyboard.KEY_Q))
			x--;
		if(Input.isKeyDown(Keyboard.KEY_D))
			x++;
		
		this.move(x * Time.delta(), y * Time.delta());
		
		World.getMainCamera().position().x = position().x;
		World.getMainCamera().position().y = position().y;
	}
	
	public void onDraw() throws IOException{
		GL11.glColor3f(1f,1f,1f);
		TextureLoader.getTexture("slender.png").bind();
		
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
		
		TextureLoader.getTexture("slender.png").unbind();
	}

}
