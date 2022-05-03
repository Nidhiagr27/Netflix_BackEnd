package com.project.exceptions;

public class DependencyFailureException extends RuntimeException{
    public DependencyFailureException(Throwable cause) {
        super(cause);
    }
}
