package game.main;
import engine.main.Engine;
import engine.world.World;
import game.entity.Slender;

public class Main extends Engine{
	
	@Override
	public void onInit(){
		World.getMainCamera().size().set(15, 15);
		World.addEntity(new Slender());
	}
	
	@Override
	public void onQuit(){
		
	}

	public static void main(String[] args) {
		new Main();
	}

}
