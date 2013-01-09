package engine.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * Un bag est une sorte d'array list, mais qui ne preserve pas l'ordre des elements
 * Classe spécialement rapide pour les jeux
 * @author Valentin 'Atrakeur' Letourneur <atrakeur@gmail.com>
 * Created 27 mai 2012 at 08:32:38
 *
 * @param <E>
 */

public class Bag<E> extends ArrayList<E> {
	
	ArrayList<Integer> toRemove;
	
	/**
	 * Construct an empty Bag with an initial capacity of 64
	 */
	public Bag(){
		super(64);
		toRemove = new ArrayList<Integer>();
	}
	
	/**
	 * Prepare to remove element without touching list
	 */
	public boolean remove(Object object) {
		int i = indexOf(object);
		if (i >= 0) {
			toRemove.add(i);
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Prepare to remove a specific element at given index
	 */
	public E remove(int index){
		if(index < 0 || index >= size())
			return null;
		
		toRemove.add(index);
		return get(index);
	}
	
	/**
	 * Remove all elements that need to be
	 */
	public void clean(){
		if(toRemove.size() == 0)
			return;
		
		//sort in ascending id order
		Collections.sort(toRemove);
		//Delete in descending id order (so upcoming id don't change)
		for(int i = toRemove.size()-1; i >= 0; i--){
			super.remove(toRemove.get(i));
		}
		//clean up toRemove list
		toRemove.clear();
	}
	
	/**
	 * Clear all elements of the list, without using the toRemove list
	 */
	public void clear(){
		for(int i = toRemove.size()-1; i >= 0; i--){
			super.remove(i);
		}
	}
	
}
