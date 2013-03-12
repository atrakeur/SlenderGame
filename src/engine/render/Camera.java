package engine.render;

import engine.entity.Entity;

/**
 * Camera class
 * 
 * A Camera act just like a virtual camera.
 * This special entity cause the Render to film the World at his position
 * 
 * The viewport is set as his center is at the camera position, and his in-game
 * size is set as the camera size.
 * 
 * @author Valentin 'Atrakeur' Letourneur <atrakeur@gmail.com>
 * Created 21 déc. 2012 at 06:42:56
 *
 */
public class Camera extends Entity{
	
	/**
	 * Translate screen X coordinate to world X coordinate
	 * @param screenX position on screen (in pixel)
	 * @return the world position
	 */
	public float screenToWorldX(float screenX){
		return (screenX-Render.width()/2)/Render.width() * size().x + position().x;
	}
	
	/**
	 * Translate screen Y coordinate to world Y coordinate
	 * @param screenY position on screen (in pixel)
	 * @return the world position
	 */
	public float screenToWorldY(float screenY){
		return ((screenY-Render.height()/2)/Render.height() * size().y + position().y) / Render.ratio();
	}
	
	/**
	 * Translate world X coordinates to screen
	 * @param worldX position in world
	 * @return the screen position
	 */
	public float worldToScreenX(float worldX){
		return ((worldX - position().x) / size().x) * Render.width() + Render.width()/2;
	}
	
	/**
	 * Translate world Y coordinates to screen
	 * @param worldY position in world
	 * @return the screen position
	 */
	public float worldToScreenY(float worldY){
		return ((worldY - position().y) / size().y) * Render.height() + Render.height()/2;
	}

}
