package com.lcc.cms.model;

public class CmsException extends RuntimeException {
    public CmsException() {
    }

    public CmsException(String message) {
        super(message);
    }

    public CmsException(String message, Throwable cause) {
        super(message, cause);
    }

    public CmsException(Throwable cause) {
        super(cause);
    }

    public CmsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
