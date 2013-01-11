package engine.physics;

import engine.entity.Entity;
import engine.math.Vector2;

public class Physics {
	
	/**
	 * A temp Vector2 that is used in atomic operations for GC performance 
	 */
	private static Vector2 tmpV = new Vector2();
	
	/**
	 * Check if that entity collide with a point
	 * @param point
	 * @param e
	 * @return
	 */
	public static boolean checkPointCollision(Vector2 point, Entity e){
			
		//check against sphere
		tmpV.x = point.x - e.position().x;
		tmpV.y = point.y - e.position().y;
			
		if(tmpV.lengthSquared() > e.size().lengthSquared())
			return false;
		
		
		float minX = e.position().x - e.size().x / 2;
		float maxX = e.position().x + e.size().x / 2;
		//check against X bounds
		if(point.x < minX && point.x > maxX)
			return false;
		
		
		float minY = e.position().y - e.size().y / 2;
		float maxY = e.position().y + e.size().y / 2;
		//check against Y bounds
		if(point.y < minY && point.y > maxY)
			return false;
		
		return true;
		
		
	}

}
