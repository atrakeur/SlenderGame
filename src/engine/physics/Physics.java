package engine.physics;

import java.util.ArrayList;
import java.util.Iterator;

import engine.entity.Entity;
import engine.math.Vector2;
import engine.world.World;

public class Physics {
	
	/**
	 * Two temp Vector2 that are used in atomic operations for GC performance 
	 */
	private static Vector2 tmpV1 = new Vector2();
	private static Vector2 tmpV2 = new Vector2();
	
	/**
	 * Check if that entity collide with a point
	 * @param point
	 * @param e
	 * @return
	 */
	public static boolean checkPointCollision(Vector2 point, Entity e){
			
		//check against sphere
		tmpV1.x = point.x - e.position().x;
		tmpV1.y = point.y - e.position().y;
			
		if(tmpV1.lengthSquared() > e.size().lengthSquared())
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
	
	/**
	 * Check if two entity overlaps each other
	 * @param e1
	 * @param e2
	 * @return
	 */
	public static boolean checkEntityCollision(Entity e1, Entity e2){
		
		//check against sphere
		tmpV1.x = e2.position().x - e1.position().x;
		tmpV1.y = e2.position().y - e1.position().y;
		
		tmpV2.x = e2.size().x + e1.size().x;
		tmpV2.y = e2.size().y + e1.size().y;
		
		if(tmpV1.lengthSquared() > tmpV2.lengthSquared())
			return false;
		
		//check x bounds
		float e1min = e1.position().x - e1.size().x;
		float e2min = e2.position().x - e2.size().x;
		float e1max = e1.position().x + e1.size().x;
		float e2max = e2.position().x + e2.size().x;
		
		if(e1max > e2min || e2max > e1min)
			return false;
		
		//check y bounds
		e1min = e1.position().y - e1.size().y;
		e2min = e2.position().y - e2.size().y;
		e1max = e1.position().y + e1.size().y;
		e2max = e2.position().y + e2.size().y;
		
		if(e1max > e2min || e2max > e1min)
			return false;
		
		return true;
		
	}
	
	public static Entity[] pointCast(Vector2 point){
		ArrayList<Entity> list = new ArrayList<Entity>();
		
		
		Iterator<Entity> it = World.getEntityIterator();
		Entity e;
		
		while(it.hasNext()){
			e = it.next();
			
			if(Physics.checkPointCollision(point, e))
				list.add(e);
		}
		
		Entity[] retVal = new Entity[list.size()];
		list.toArray(retVal);
		return retVal;
	}

}
