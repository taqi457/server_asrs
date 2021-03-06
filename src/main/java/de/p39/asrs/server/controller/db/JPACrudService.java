/**
 * 
 */
package de.p39.asrs.server.controller.db;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import de.p39.asrs.server.model.BaseEntity;
import de.p39.asrs.server.model.auth.Role;
import de.p39.asrs.server.model.auth.User;

/**
 * @author Adrian Rebmann <adrianrebmann@gmail.com>
 *
 */
public class JPACrudService implements CrudFacade {

	private static final Logger LOGGER = Logger.getLogger(JPACrudService.class);
	/**
	 * the entity manager of the crud service
	 */
	private EntityManagerFactory emf;
	private String persistenceUnit;

	/**
	 * 
	 */
	public JPACrudService(String persistenceUnit) {
		this.persistenceUnit = persistenceUnit;
		this.init();
	}

	public JPACrudService() {
		persistenceUnit = "server";
		init();
	}

	private void init() {
		this.emf = Persistence.createEntityManagerFactory(persistenceUnit);
		this.findOrCreateUser();
	}

	private void findOrCreateUser() {
		try {
			String q = "SELECT e FROM " + User.class.getName() + " e WHERE username = :name";
			EntityManager em = this.emf.createEntityManager();
			Query query = em.createQuery(q);
			query.setParameter("name", "admin");
			query.getResultList().get(0);
		} catch (IndexOutOfBoundsException | NullPointerException e) {
			User u = new User();
			u.setUsername("admin");
			BCryptPasswordEncoder hash = new BCryptPasswordEncoder();
			String password = hash.encode("NuH/GP;+h<#8is?");
			u.setPassword(password);
			Set<Role> sr = new HashSet<>();
			sr.add(Role.ADMIN);
			u.setRoles(sr);
			this.create(u);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.p39.asrs.server.controller.db.CrudFacade#find(java.io.Serializable,
	 * java.lang.Class)
	 */
	@Override
	public <K extends Serializable, T extends BaseEntity<K>> T find(K id, Class<T> clazz) {
		EntityManager em = this.emf.createEntityManager();
		EntityTransaction transaction = null;
		T t = null;
		try {
			transaction = em.getTransaction();
			transaction.begin();
			t = em.find(clazz, id);
			transaction.commit();
		} catch (RuntimeException e) {
			LOGGER.log(Level.ERROR, e.getMessage(), e);
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
				throw new PersistenceException(e.getMessage());
			}
		} finally {
			em.close();
		}
		return t;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.p39.asrs.server.controller.db.CrudFacade#create(de.p39.asrs.server.
	 * model.ABasicObject)
	 */
	@Override
	public <K extends Serializable, T extends BaseEntity<K>> T create(T o) {
		EntityManager em = this.emf.createEntityManager();
		EntityTransaction transaction = null;
		T t = null;
		try {
			transaction = em.getTransaction();
			transaction.begin();
			if (!em.contains(o)) {
				o = em.merge(o);
			}
			em.persist(o);
			t = em.merge(o);
			transaction.commit();
		} catch (RuntimeException e) {
			LOGGER.log(Level.ERROR, e.getMessage(), e);
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
				throw new PersistenceException(e.getMessage());
			}
		} finally {
			em.close();
		}
		return t;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.p39.asrs.server.controller.db.CrudFacade#delete(de.p39.asrs.server.
	 * model.ABasicObject)
	 */
	@Override
	public <K extends Serializable, T extends BaseEntity<K>> void delete(K id, Class<T> clazz) {
		EntityManager em = this.emf.createEntityManager();
		EntityTransaction transaction = null;
		T t = null;
		try {
			transaction = em.getTransaction();
			transaction.begin();
			t = em.find(clazz, id);
			em.remove(t);
			em.flush();
			transaction.commit();
		} catch (RuntimeException e) {
			LOGGER.log(Level.ERROR, e.getMessage(), e);
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
				throw new PersistenceException(e.getMessage());
			}
		} finally {
			em.close();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.p39.asrs.server.controller.db.CrudFacade#update(de.p39.asrs.server.
	 * model.ABasicObject)
	 */
	@Override
	public <K extends Serializable, T extends BaseEntity<K>> T update(T o) {
		EntityManager em = this.emf.createEntityManager();
		EntityTransaction transaction = null;
		T t = null;
		try {
			transaction = em.getTransaction();
			transaction.begin();
			t = em.merge(o);
			transaction.commit();
		} catch (RuntimeException e) {
			LOGGER.log(Level.ERROR, e.getMessage(), e);
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
				throw new PersistenceException(e.getMessage());
			}
		} finally {
			em.close();
		}
		return t;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.p39.asrs.server.controller.db.CrudFacade#findOrCreate(de.p39.asrs.
	 * server.model.ABasicObject, java.io.Serializable)
	 */
	@Override
	public <K extends Serializable, T extends BaseEntity<K>> T findOrCreate(T object, K key) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.p39.asrs.server.controller.db.CrudFacade#findAll(java.lang.Class)
	 */
	@Override
	public <K extends Serializable, T extends BaseEntity<K>> List<T> findAll(Class<T> clazz) {
		EntityManager em = this.emf.createEntityManager();
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<T> criteria = builder.createQuery(clazz);
		Root<T> entityRoot = criteria.from(clazz);
		criteria.select(entityRoot);
		List<T> result = em.createQuery(criteria).getResultList();
		em.close();
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.p39.asrs.server.controller.db.CrudFacade#updateAll(java.util.
	 * Collection)
	 */
	@Override
	public <K extends Serializable, T extends BaseEntity<K>> Collection<T> updateAll(Collection<T> objects) {
		List<T> objs = new ArrayList<>();
		for (T obj : objects) {
			T managedRef = update(obj);
			objs.add(managedRef);
		}
		return objs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.p39.asrs.server.controller.db.CrudFacade#deleteAll(java.util.
	 * Collection, java.lang.Class)
	 */
	@Override
	public <K extends Serializable, T extends BaseEntity<K>> void deleteAll(Collection<K> objects, Class<T> clazz) {
		for (K obj : objects) {
			delete(obj, clazz);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.p39.asrs.server.controller.db.CrudFacade#createAll(java.util.
	 * Collection)
	 */
	@Override
	public <K extends Serializable, T extends BaseEntity<K>> void createAll(Collection<T> o) {
		for (T obj : o) {
			create(obj);
		}
	}

	@Override
	public <K extends Serializable, T extends BaseEntity<K>> int count(Class<T> clazz) {
		return this.findAll(clazz).size();
	}

	/**
	 * @return the emf
	 */
	public EntityManagerFactory getEmf() {
		return emf;
	}

	@Override
	public Query createQuery(String jpql) {
		EntityManager em = this.emf.createEntityManager();
		return em.createQuery(jpql);
	}

}
