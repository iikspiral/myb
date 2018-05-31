package com.sxca.myb.common.exception;

public class SynchroRegularException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public SynchroRegularException() {
		super();
	}

	public SynchroRegularException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public SynchroRegularException(String message, Throwable cause) {
		super(message, cause);
	}

	public SynchroRegularException(String message) {
		super(message);
	}

	public SynchroRegularException(Throwable cause) {
		super(cause);
	}

	
}
