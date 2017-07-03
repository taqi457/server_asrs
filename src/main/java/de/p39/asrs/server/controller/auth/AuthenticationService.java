package de.p39.asrs.server.controller.auth;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import de.p39.asrs.server.controller.db.dao.AuthenticationDAO;
import de.p39.asrs.server.model.auth.User;

@Service
public class AuthenticationService {

	@Autowired 
	AuthenticationDAO dao;
	
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>(dao.findAllRoles()));
        dao.save(user);
    }

    public User findByUsername(String username) {
        return dao.findByUsername(username);
    }
}
