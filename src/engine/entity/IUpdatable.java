package engine.entity;

/**
 * Represent an entity that must be updated by the engine
 * @author Valentin 'Atrakeur' Letourneur <atrakeur@gmail.com>
 * Created 8 mars 2013 at 18:23:51
 *
 */
public interface IUpdatable extends IEntitable{
	
	/**
	 * Called when the entity need to update
	 */
	public void onUpdate();

}
