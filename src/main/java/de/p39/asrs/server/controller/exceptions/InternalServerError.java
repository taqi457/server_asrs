package de.p39.asrs.server.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerError extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -405331873748733782L;
	
	public InternalServerError(String msg){
		super(msg);
	}
	
}
