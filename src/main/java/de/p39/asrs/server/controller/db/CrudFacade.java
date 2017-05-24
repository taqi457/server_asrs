package de.p39.asrs.server.controller.db;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import de.p39.asrs.server.model.ABasicObject;

/**
 * This interface provides signatures for creating, reading, updating and
 * deleting persistent objects
 * 
 * @author Adrian Rebmann <adrianrebmann@gmail.com>
 *
 */
public interface CrudFacade {
	/**
	 * finds a persistent object
	 * 
	 * @param id
	 * @param clazz
	 * @return the element having this given id and type
	 */
	public <K extends Serializable, T extends ABasicObject<K>> T find(K id, Class<T> clazz);

	/**
	 * creates a persistent object
	 * 
	 * @param o
	 * @return the newly created object
	 */
	public <K extends Serializable, T extends ABasicObject<K>> T create(T o);

	/**
	 * deletes an object
	 * @param id
	 * @param clazz
	 */
	public <K extends Serializable, T extends ABasicObject<K>> void delete(K id,  Class<T> clazz);

	/**
	 * updates an object
	 * 
	 * @param o
	 * @return the updated object
	 */
	public <K extends Serializable, T extends ABasicObject<K>> T update(T o);

	/**
	 * tries to find an object, if it is not found it is created
	 * 
	 * @param object
	 * @param key
	 * @return the found object or a newly created object in case it was
	 *         non-existent
	 */
	public <K extends Serializable, T extends ABasicObject<K>> T findOrCreate(T object, K key);

	/**
	 * finds all elements of a specified type
	 * 
	 * @param clazz
	 * @return a list of all found objects having the specified type
	 */
	public <K extends Serializable, T extends ABasicObject<K>> List<T> findAll(Class<T> clazz);

	/**
	 * updates a collection of objects
	 * 
	 * @param objects
	 * @return a collection of the updated objects
	 */
	public <K extends Serializable, T extends ABasicObject<K>> Collection<T> updateAll(Collection<T> objects);

	/**
	 * deletes a collection of objects
	 * 
	 * @param objects
	 * @param clazz
	 */
	public <K extends Serializable, T extends ABasicObject<K>> void deleteAll(Collection<K> objects, Class<T> clazz);

	/**
	 * persists a series of objects
	 * 
	 * @param o
	 */
	public <K extends Serializable, T extends ABasicObject<K>> void createAll(Collection<T> o);
	
	/**
	 * 
	 * @param clazz
	 * @return the number of objects with the specified type
	 */
	public <K extends Serializable, T extends ABasicObject<K>> int count(Class<T> clazz);
	
	/*********************************************************************
	 * below there are more specific methods to access persistent objects
	 *********************************************************************/
	
	

}
