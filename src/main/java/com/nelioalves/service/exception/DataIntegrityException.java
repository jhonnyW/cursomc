package com.nelioalves.service.exception;

public class DataIntegrityException  extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 5105499443440999167L;

    public DataIntegrityException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataIntegrityException(String message) {
        super(message);
    }

}
