package com.javarush.jira.common.error;

import org.springframework.lang.NonNull;

public class AppException extends RuntimeException {

    public AppException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppException(@NonNull String message) {
        super(message);
    }

}
