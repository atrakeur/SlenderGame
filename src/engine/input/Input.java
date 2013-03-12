package engine.input;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import engine.debug.Profiler;
import engine.math.Vector2;
import engine.world.World;

public class Input {
	
	public static final Vector2 mousePosition = new Vector2();
	
	
	public static void update(){
		Profiler.startProfile("Engine/Update/Input");
		mousePosition.x = Mouse.getX();
		mousePosition.y = Mouse.getY();
		Profiler.endProfile("Engine/Update/Input");
	}
	
	public static boolean isButtonDown(int button){
		return Mouse.isButtonDown(button);
	}
	
	public static boolean isKeyDown(int key){
		return Keyboard.isKeyDown(key);
	}

}
