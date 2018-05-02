package com.myretail.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "ResourceId in request header and body doesn't match.")
public class ResourceMisMatchException extends RuntimeException {

	/**
	 * SUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public ResourceMisMatchException() {
		// TODO Auto-generated constructor stub
	}

}
