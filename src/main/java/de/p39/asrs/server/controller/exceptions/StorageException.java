package de.p39.asrs.server.controller.exceptions;

public class StorageException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7882905581724085389L;

	public StorageException() {
		
	}

	public StorageException(String string) {
		super(string);
	}

}
