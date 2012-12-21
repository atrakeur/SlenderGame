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
	
	public abstract void onStart();
	
	public abstract void onUpdate();
	
	public abstract void onDestroy();

}
