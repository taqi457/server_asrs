package de.p39.asrs.server.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundExecption extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8808407848276692356L;
   
}