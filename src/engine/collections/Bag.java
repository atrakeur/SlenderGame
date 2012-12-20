package engine.collections;

import java.util.ArrayList;
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
	
	/**
	 * Construct an empty Bag with an initial capacity of 64
	 */
	public Bag(){
		super(64);
	}
	
	/**
	 * Remove element preserving order (setting it null)
	 */
	public boolean remove(Object object) {
		int i = indexOf(object);
		if (i >= 0) {
			set(i, null);
			return true;
		} else {
			return false;
		} 
	}
	
	/**
	 * Remove all null elements
	 */
	public void clean(){
		int i = lastIndexOf(null);
		while(i >= 0){
			remove(i);
			i = lastIndexOf(null);
		}
	}
	
}
