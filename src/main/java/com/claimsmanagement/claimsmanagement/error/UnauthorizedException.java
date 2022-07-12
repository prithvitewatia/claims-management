package com.claimsmanagement.claimsmanagement.error;

public class UnauthorizedException extends RuntimeException{
    public UnauthorizedException(String unauthorized) {
        super(unauthorized);
    }
}
