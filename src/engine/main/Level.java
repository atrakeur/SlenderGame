package engine.main;

/**
 * Level class
 * 
 * Parent class for each level class
 * A level basically describe a Level in game (such as MenuLevel, LevelOne, etc)
 * When setting a new Level, World class get reseted so you can create the new level in onStart method
 * 
 * @author Valentin 'Atrakeur' Letourneur <atrakeur@gmail.com>
 *
 */
public abstract class Level {
	
	public final String levelName;
	
	public Level(String levelName){
		this.levelName = levelName;
	}
	
	/**
	 * Called when starting the new level, just after unloading the previous
	 */
	public abstract void onStart();
	
	/**
	 * Called when the level need to update, just after world update
	 */
	public abstract void onUpdate();
	
	/**
	 * Called when destroying the level, just before loading a new level or before exiting
	 */
	public abstract void onDestroy();

}
