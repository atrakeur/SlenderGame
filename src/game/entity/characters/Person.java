package game.entity.characters;

import engine.entity.Entity;
import engine.entity.IDrawable;
import engine.entity.IUpdatable;
import engine.math.Vector2;
import engine.time.Time;

/**
 * Defines a common character in game
 * 
 * @author Valentin 'Atrakeur' Letourneur <atrakeur@gmail.com>
 * Created 9 janv. 2013 at 17:16:57
 *
 */
public abstract class Person extends Entity implements IDrawable, IUpdatable{
	
	public static final int PANIC = 90;
	
	protected int fear = 0;
	protected int curiosity = 0;
	
	protected long nextMoveChoice = 0;
	Vector2 lastMove = new Vector2();
	
	public void onUpdate(){
		//Change movement direction
		if(nextMoveChoice < Time.time()){
			lastMove.x = (float) (Math.random()*2 -1);
			lastMove.y = (float) (Math.random()*2 -1);
			
			nextMoveChoice = Time.time() + 2000;
		}
		
		//Apply the chosen movement
		move(lastMove.x * Time.delta(), lastMove.y * Time.delta());
	}

}
