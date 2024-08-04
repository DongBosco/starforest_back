package com.project.starforest.util;

import org.springframework.mail.MailException;

public class BusinessLogicException extends RuntimeException {
    public BusinessLogicException(ExceptionCode code) {
        super("Business logic exception: " + code);
    }

    public BusinessLogicException(ExceptionCode exceptionCode, String failedToSendEmail, MailException e) {
    }
}
