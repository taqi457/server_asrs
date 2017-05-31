package de.p39.asrs.server.control.db;

import static org.junit.Assert.*;

import org.junit.Test;

import de.p39.asrs.server.controller.db.CrudFacade;
import de.p39.asrs.server.controller.db.JPACrudService;
import de.p39.asrs.server.model.Route;

public class DBTest {

	@Test
	public void createDbTest(){
		CrudFacade cf = new JPACrudService("server");
		assertTrue(cf.count(Route.class)==0);
	}

}
