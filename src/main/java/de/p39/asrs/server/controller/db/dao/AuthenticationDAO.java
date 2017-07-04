package de.p39.asrs.server.controller.db.dao;

import java.util.List;

import javax.persistence.Query;

import de.p39.asrs.server.controller.db.CrudFacade;
import de.p39.asrs.server.model.auth.User;

public class AuthenticationDAO {

	private CrudFacade cf;

	public AuthenticationDAO(CrudFacade cf) {
		super();
		this.cf = cf;
	}

	public User findByUsername(String username) {
		try {
			Query q = this.cf.createQuery("SELECT e FROM " + User.class.getName() + " e WHERE username = :name");
			q.setParameter("name", username);
			return (User) q.getResultList().get(0);
		} catch (NullPointerException e) {
			return null;
		}
	}
	
	public List<User> findAllUsers(){
		return this.cf.findAll(User.class);
	}

	public void save(User user) {
		this.cf.create(user);
	}

}
