package game.entity.characters;

import java.io.IOException;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import engine.entity.Entity;
import engine.entity.IDrawable;
import engine.entity.IUpdatable;
import engine.input.Input;
import engine.math.Vector2;
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
	
	private Vector2 direction = new Vector2();
	
	public void onStart() throws IOException{
		this.setName("Slender");
	}
	
	public void onUpdate(){
		direction.x = 0;
		direction.y = 0;
		
		if(Input.isKeyDown(Keyboard.KEY_S))
			direction.y--;
		if(Input.isKeyDown(Keyboard.KEY_Z))
			direction.y++;
		if(Input.isKeyDown(Keyboard.KEY_Q))
			direction.x--;
		if(Input.isKeyDown(Keyboard.KEY_D))
			direction.x++;
		
		this.move(direction.x * Time.delta(), direction.y * Time.delta());
		
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
