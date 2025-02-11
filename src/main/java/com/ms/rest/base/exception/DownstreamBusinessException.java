package com.ms.rest.base.exception;

public class DownstreamBusinessException extends RuntimeException {
    public DownstreamBusinessException(String message) {
        super(message);
    }
}
