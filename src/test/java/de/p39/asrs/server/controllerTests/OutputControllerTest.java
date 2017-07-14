package de.p39.asrs.server.controllerTests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import de.p39.asrs.server.controller.Application;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class OutputControllerTest {

	@Autowired
	private MockMvc mockMvc;
/* does not work because of DB!
	@Test
	public void getRouteBadRequest() throws Exception {
		this.mockMvc.perform(get("/route")).andExpect(status().isBadRequest());
	}

	@Test
	public void getSiteBadRequest() throws Exception {
		this.mockMvc.perform(get("/site")).andExpect(status().isBadRequest());
	}*/

}
