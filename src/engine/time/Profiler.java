package engine.time;

import java.util.ArrayList;

class ProfilePoint{
	
	public final String name;
	public final int level;
	public int call;
	public long time;
	
	public ProfilePoint(String name, int level){
		this.name = name;
		this.level = level;
	}
	
	public boolean equals(ProfilePoint pp){
		return pp.level == this.level && pp.name.equals(this.name);
	}
	
}

public class Profiler {
	
	

}
