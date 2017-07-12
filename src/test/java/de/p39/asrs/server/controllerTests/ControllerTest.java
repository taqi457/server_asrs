package de.p39.asrs.server.controllerTests;

import org.junit.Ignore;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import de.p39.asrs.server.controller.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class ControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	@Ignore
	public void testExample() throws Exception {
		this.mvc.perform(get("/loginuser").accept(MediaType.TEXT_PLAIN)).andExpect(status().isOk());
		this.mvc.perform(post("/kml").accept(MediaType.MULTIPART_FORM_DATA)).andExpect(status().isOk());
	}

}
