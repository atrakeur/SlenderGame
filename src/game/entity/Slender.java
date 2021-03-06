package game.entity;

import java.awt.Rectangle;
import java.io.IOException;

import engine.main.Engine;
import game.entity.characters.Man;
import game.entity.characters.Woman;
import game.levels.GameLevel;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import engine.entity.Entity;
import engine.entity.IDrawable;
import engine.entity.IGuiable;
import engine.entity.IUpdatable;
import engine.gui.GUI;
import engine.input.Input;
import engine.math.Vector2;
import engine.physics.Physics;
import engine.textures.TextureLoader;
import engine.time.Time;
import engine.world.World;
import game.entity.characters.Person;

public class Slender extends Entity implements IDrawable, IUpdatable, IGuiable{
	
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
		
		if(Input.isKeyDown(Keyboard.KEY_SPACE)){
			Entity[] touchs = Physics.pointCast(position());
			for(int i = 0; i < touchs.length; i++)
				if(touchs[i] instanceof Person){
					((Person)touchs[i]).kill();
				}
				
		}
		
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

	public String toString() {
		return "Slender []";
	}

	public void onGUI() {
		try {
			if(GUI.button(new Rectangle(10, 10, 150, 65), TextureLoader.getTexture("buttons/button1.png"))) {
				World.addEntity(new Woman());
				World.addEntity(new Man());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
