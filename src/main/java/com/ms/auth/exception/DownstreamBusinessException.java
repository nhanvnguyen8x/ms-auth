package com.ms.auth.exception;

public class DownstreamBusinessException extends RuntimeException {
    public DownstreamBusinessException(String message) {
        super(message);
    }
}
