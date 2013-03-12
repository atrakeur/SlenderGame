package engine.exceptions;

/**
 * Exception used exclusively when a engine internal error has occured
 * 
 * @author Valentin 'Atrakeur' Letourneur <atrakeur@gmail.com>
 * Created 8 mars 2013 at 18:25:45
 *
 */
public class GameException extends Exception{
	
	public GameException(){}
	
	public GameException(String err){super(err);};

}
