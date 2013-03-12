package engine.entity;

import java.io.IOException;

/**
 * Define an entity that can be Drawed on screen
 * 
 * @author Valentin 'Atrakeur' Letourneur <atrakeur@gmail.com>
 * Created 8 mars 2013 at 18:22:01
 *
 */
public interface IDrawable extends IEntitable{
	
	/**
	 * Called when the entity need to draw itself
	 * @throws IOException
	 */
	public void onDraw() throws IOException;

}
