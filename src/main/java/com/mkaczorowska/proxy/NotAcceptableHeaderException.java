package com.mkaczorowska.proxy;

public class NotAcceptableHeaderException extends RuntimeException {
    public NotAcceptableHeaderException(String header) {
        super("Header " + header + " is not acceptable.");
    }
}
