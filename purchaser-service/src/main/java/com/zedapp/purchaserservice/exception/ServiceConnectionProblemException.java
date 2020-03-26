package com.zedapp.purchaserservice.exception;

public class ServiceConnectionProblemException extends Exception {
    public ServiceConnectionProblemException() {

    }

    public ServiceConnectionProblemException(String msg) {
        super(msg);
    }
}
