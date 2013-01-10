package engine.time;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

class ProfilePoint{
	
	public final String name;
	public int calls;
	public long lastStartTime;
	public long lastExecTime;
	public long cumulTime;
	
	public ProfilePoint(String name){
		this.name = name;
	}
	
	public boolean equals(ProfilePoint pp){
		return pp.name.equals(this.name);
	}
	
	public String toString(){
		return name+"|"+calls+"|"+cumulTime+"|"+(cumulTime/calls)+"\n";
	}
	
}

public class Profiler {
	
	private static HashMap<String, ProfilePoint> points = new HashMap<String, ProfilePoint>();
	
	/**
	 * Mark a new profile point
	 * @param point
	 */
	public static void startProfile(String point){
		if(!points.containsKey(point))
			points.put(point, new ProfilePoint(point));
		
		points.get(point).lastStartTime = Time.time();
	}
	
	/**
	 * Mark a end of a profile point
	 * @param point
	 */
	public static void endProfile(String point){
		ProfilePoint p = points.get(point);
		if(p == null)
			return;
		
		p.lastExecTime = Time.time() - p.lastStartTime;
		p.cumulTime += p.lastExecTime;
		p.calls++;
	}
	
	/**
	 * Output profile point summary into a String
	 * @return a string representation of all profile points
	 */
	public static String output(){
		StringBuilder sb = new StringBuilder();
		
		for(Entry<String, ProfilePoint> e : points.entrySet())
			sb.append(e.getValue().toString());
		
		return sb.toString();
	}

}
