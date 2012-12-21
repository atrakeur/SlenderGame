package engine.time;

import engine.exceptions.GameException;

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
	
	public static void startFrame(){
		lastDelta = (float)(time() - lastFrameStart) / 1000f;
		lastFrameStart = System.currentTimeMillis();
		frameCount++;
	}
	
	public static long time(){
		return System.currentTimeMillis();
	}
	
	public static float delta(){
		return lastDelta;
	}
	
	public static long getSleep(){
		long sleep = 1000/targetFPS - (time() - lastFrameStart);
		if(sleep < 0)
			return 0;
		
		return sleep;
	}

}
