package game.main;
import engine.entity.Entity;
import engine.main.Engine;
import engine.world.World;
import game.entity.Slender;

public class Main extends Engine{
	
	public void onInit(){
		World.addEntity(new Slender());
	}
	
	public void onQuit(){
		
	}

	public static void main(String[] args) {
		new Main();
	}

}
