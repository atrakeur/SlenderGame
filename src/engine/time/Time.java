package engine.time;

import engine.exceptions.GameException;

/**
 * Used by the engine to manage time
 * 
 * @author Valentin 'Atrakeur' Letourneur <atrakeur@gmail.com>
 * Created 8 mars 2013 at 18:37:31
 *
 */
public class Time {
	
	private static boolean isInit;
	
	private static int frameCount;
	private static long lastFrameStart;
	private static float lastDelta;
	
	public static int targetFPS = 30;
	
	public static void init() throws GameException{
		if(isInit)
			throw new GameException("Can't init Time twice");
		
		lastFrameStart = System.currentTimeMillis();
		lastDelta = 0;
	}
	
	/**
	 * Called by the engine when a new frame start
	 */
	public static void startFrame(){
		lastDelta = (float)(time() - lastFrameStart) / 1000f;
		lastFrameStart = System.currentTimeMillis();
		frameCount++;
	}
	
	/**
	 * @return the current time
	 */
	public static long time(){
		return System.currentTimeMillis();
	}
	
	/**
	 * @return the delta passed since the last frame
	 */
	public static float delta(){
		return lastDelta;
	}
	
	/**
	 * @return the time the engine will sleep at the end of the frame
	 */
	public static long getSleep(){
		long sleep = 1000/targetFPS - (time() - lastFrameStart);
		if(sleep < 0)
			return 0;
		
		return sleep;
	}

}
