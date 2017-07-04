package de.p39.asrs.server.model.auth;

import java.util.Set;

import javax.persistence.*;

import de.p39.asrs.server.model.BaseEntity;

/**
 * based on https://hellokoding.com/registration-and-login-example-with-spring-security-spring-boot-spring-data-jpa-hsql-jsp/
 * 
 * @author adrianrebmann
 *
 */
@Entity

public class Role extends BaseEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8543288642758665212L;

	private String name;
	private Set<User> users;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return super.getId();
	}

	public void setId(Long id) {
		super.setId(id);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToMany(mappedBy = "roles")
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

}
