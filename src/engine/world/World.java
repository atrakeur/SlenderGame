package engine.world;

import java.util.ArrayList;
import java.util.Iterator;

import engine.collections.Bag;
import engine.entity.Entity;
import engine.entity.IEntitable;
import engine.entity.IUpdatable;
import engine.exceptions.GameException;
import engine.render.Camera;
import engine.world.background.NoBackground;
import engine.world.background.Background;

/**
 * Hold the game world
 * 
 * @author Valentin 'Atrakeur' Letourneur <atrakeur@gmail.com>
 * Created 21 déc. 2012 at 19:34:01
 *
 */
public class World{
	
	private static boolean isInit = false;
	
	private static Background background;
	
	public static final int LAYER_COUNT = 3;
	private static Layer[] layers;
	
	/*
	 * Hold dynamics objects (characters, traps)
	 */
	private static Bag<Entity> entities;
	
	private static Camera mainCamera;
	
	/*************************************************
	 * World gest
	 **************************************************/
	
	private World(){}
	
	/**
	 * Init the game world
	 */
	public static void init() throws Exception{
		if(isInit)
			throw new GameException("Can't init Render twice");
		
		background = new NoBackground();
		
		//create entity and camera
		entities = new Bag<Entity>();
		mainCamera = new Camera();
		
		//create layers
		layers = new Layer[LAYER_COUNT];
		for(int i = 0; i < LAYER_COUNT; i++)
			layers[i] = new Layer(i);
		
		
		isInit = true;
	}
	
	/**
	 * Re inti the game worl (destroy it, then reInit it from scratch!)
	 * @throws Exception
	 */
	public static void reInit() throws Exception{
		destroy();
		isInit = false;
		init();
	}
	
	/**
	 * Destroy the game world
	 */
	public static void destroy(){
		for(IEntitable e : entities)
			e.onDestroy();
	}
	
	/*************************************************
	 * Layer gest
	 **************************************************/
	
	/**
	 * Return the layer at the given index
	 */
	public static Layer getLayer(int index) throws GameException{
		if(index >= LAYER_COUNT)
			throw new GameException("Can't get Layer higher than "+LAYER_COUNT);
		
		return layers[index];
	}
	
	/*************************************************
	 * Tiles gest
	 **************************************************/
	
	/**
	 * Check if given position is Blocked by a tile in any of the layers
	 */
	public static boolean isPositionBlocked(float x, float y){
		boolean blocked = false;
		int layerNumber = 0;
		
		while(!blocked && layerNumber <= LAYER_COUNT){
			try {
				blocked = getLayer(layerNumber).getNearestTile(x, y).isBlocked();
			} catch (GameException e) {
			}
		}
		
		return blocked;
	}
	
	/*************************************************
	 * Entity gest
	 **************************************************/
	
	/**
	 * Get an iterator on entities
	 */
	public static Iterator<Entity> getEntityIterator(){
		return entities.iterator();
	}
	
	/**
	 * Add an entity
	 */
	public static void addEntity(Entity e){
		entities.add(e);
		e.onCreate();
	}
	
	/**
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
	
	/**
	 * Remove an entity
	 */
	public static void removeEntity(IEntitable e){
		if(entities.contains(e)){
			e.onDestroy();
			entities.remove(e);
		}
	}
	
	/**
	 * @return the first entity with that name
	 */
	public static IEntitable findEntityByName(String name){
		for(IEntitable e : entities)
			if(e.getName().equals(name))
				return e;
		
		return null;
	}
	
	/**
	 * @return all entities with that name
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
	
	/**
	 * @return the first entity of the given class
	 */
	public static <T> T findEntityByType(Class<T> c){
		for(IEntitable e : entities)
			if(c.isInstance(e))
				return (T)e;
		
		return null;
	}
	
	/**
	 * @return all entity of the given class
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

	/**
	 * @param background the background to set
	 */
	public static void setBackground(Background background) {
		if(background == null)
			background = new NoBackground();
		
		World.background = background;
	}

	/**
	 * @return the background
	 */
	public static Background getBackground() {
		return background;
	}

}
