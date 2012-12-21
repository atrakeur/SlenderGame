package engine.time;

public class Time {
	
	private static int frameCount;
	private static long lastFrameStart;
	private static float lastDelta;
	
	public static void startFrame(){
		
		frameCount++;
	}

}
