package com.zedapp.purchaserservice.exception;

public class DatabaseProblem extends Exception {
    public DatabaseProblem() {

    }

    public DatabaseProblem(String msg) {
        super(msg);
    }
}
