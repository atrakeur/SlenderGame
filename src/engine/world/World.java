package engine.world;

import java.util.ArrayList;
import java.util.Iterator;

import engine.collections.Bag;
import engine.entity.Entity;
import engine.entity.IEntitable;
import engine.entity.IUpdatable;
import engine.exceptions.GameException;
import engine.render.Camera;


public class World{
	
	private static boolean isInit = false;
	
	/*
	 * Hold static game map
	 */
	private static Tile[][] map;
	
	/*
	 * Hold dynamics objects (characters, traps)
	 */
	private static Bag<Entity> entities;
	
	private static Camera mainCamera;
	
	/*************************************************
	 * World gest
	 **************************************************/
	
	private World(){}
	
	/*
	 * Init the game world
	 */
	public static void init() throws Exception{
		if(isInit)
			throw new GameException("Can't init Render twice");
		
		map = new Tile[0][0];
		entities = new Bag<Entity>();
		mainCamera = new Camera();
		
		isInit = true;
	}
	
	/*
	 * Destroy the game world
	 */
	public static void destroy(){
		for(IEntitable e : entities)
			e.onDestroy();
	}
	
	/*************************************************
	 * Entity gest
	 **************************************************/
	
	/*
	 * Get an iterator on entities
	 */
	public static Iterator<Entity> getEntityIterator(){
		return entities.iterator();
	}
	
	/*
	 * Add an entity
	 */
	public static void addEntity(Entity e){
		entities.add(e);
		e.onCreate();
	}
	
	/*
	 * Update all entities
	 */
	public static void update(){
		//Update all entities
		for(IEntitable e : entities)
			if(e instanceof IUpdatable)
				((IUpdatable)e).onUpdate();
		
		//Update main camera (if updatable)
		if(getMainCamera() instanceof IUpdatable)
			((IUpdatable)getMainCamera()).onUpdate();
	}
	
	/*
	 * Remove an entity
	 */
	public static void removeEntity(IEntitable e){
		if(entities.contains(e)){
			e.onDestroy();
			entities.remove(e);
		}
	}
	
	/*
	 * Return the first entity with that name
	 */
	public static IEntitable findEntityByName(String name){
		for(IEntitable e : entities)
			if(e.getName().equals(name))
				return e;
		
		return null;
	}
	
	/*
	 * Return all entities with that name
	 */
	public static IEntitable[] findEntitiesByName(String name){
		ArrayList<Entity> retEnt = new ArrayList<Entity>();
		for(Entity e : entities)
			if(e.getName().equals(name))
				retEnt.add(e);
		
		IEntitable[] retEnt2 = new IEntitable[retEnt.size()];
		for(int i = 0; i < retEnt.size(); i++)
			retEnt2[i] = retEnt.get(i);
		
		return retEnt2;
	}
	
	/*
	 * Return the first entity of the given class
	 */
	public static <T> T findEntityByType(Class<T> c){
		for(IEntitable e : entities)
			if(c.isInstance(e))
				return (T)e;
		
		return null;
	}
	
	/*
	 * Return all entity of the given class
	 */
	public static <T> ArrayList<T> findEntitiesByType(Class<T> c){
		ArrayList<T> retEnt = new ArrayList<T>();
		for(IEntitable e : entities)
			if(c.isInstance(e))
				retEnt.add((T)e);
		
		return retEnt;
	}

	public static void setMainCamera(Camera mainCamera) {	World.mainCamera = mainCamera;	}

	public static Camera getMainCamera() {	return mainCamera;	}

}
