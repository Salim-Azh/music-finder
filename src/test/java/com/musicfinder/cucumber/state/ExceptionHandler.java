package com.musicfinder.cucumber.state;

public class ExceptionHandler {
    private boolean expectException = false;
    private Exception exception;

    public void expectException() {
        expectException = true;
    }

    public void set(RuntimeException e) {
        if (!expectException) {
            throw e;
        }
        exception = e;
    }

    public Exception getException() {
        return exception;
    }
}
