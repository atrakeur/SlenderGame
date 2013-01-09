package engine.render;

import java.util.Comparator;

import engine.entity.IDrawable;

class SortByY implements Comparator<IDrawable>{

	public int compare(IDrawable arg0, IDrawable arg1) {
		if(arg1.position().y - arg0.position().y > 0)
			return 1;
		
		return -1;
	}

}
