package com.mynews.common.exception;

public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 4307160721883700331L;
	public ServiceException() {
		super();
	}
	public ServiceException(String message) {
		super(message);
	}
	public ServiceException(Throwable cause) {
		super(cause);
	}

}
