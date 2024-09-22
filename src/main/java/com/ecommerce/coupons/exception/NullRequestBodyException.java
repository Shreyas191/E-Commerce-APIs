package com.ecommerce.coupons.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NullRequestBodyException extends RuntimeException {

    public NullRequestBodyException(String message) {
        super(message);
    }
}
