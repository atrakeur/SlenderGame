package engine.entity;

import java.io.IOException;

public interface IDrawable extends IEntitable{
	
	public void onDraw() throws IOException;

}
