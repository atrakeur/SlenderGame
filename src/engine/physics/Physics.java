package engine.physics;

import java.util.Iterator;

import engine.entity.Entity;
import engine.math.Vector2;
import engine.world.World;

public class Physics {
	
	public static boolean checkPointCollision(Vector2 point){
		boolean found = false;
		
		Iterator<Entity> it = World.getEntityIterator();
		Entity e;
		
		Vector2 distance = new Vector2();
		
		while(it.hasNext()){
			e = it.next();
			
			//check against sphere
			distance.x = point.x - e.position().x;
			distance.y = point.y - e.position().y;
			
			if(distance.lengthSquared() > e.size().lengthSquared()){
				continue;
			}
			
			//check against bounds
		}
		
		return found;
	}

}
