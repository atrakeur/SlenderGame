package game.entity;

import java.io.IOException;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import engine.entity.Entity;
import engine.entity.IDrawable;
import engine.entity.IUpdatable;
import engine.textures.TextureLoader;
import engine.world.World;

public class Target extends Entity implements IDrawable, IUpdatable{

	public void onDraw() throws IOException {
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
	}

	public void onUpdate() {
		position().x = World.getMainCamera().screenToWorldX(Mouse.getX());
		position().y = World.getMainCamera().screenToWorldY(Mouse.getY());
	}

}
