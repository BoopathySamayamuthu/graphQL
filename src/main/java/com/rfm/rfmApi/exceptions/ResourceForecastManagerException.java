package com.rfm.rfmApi.exceptions;

public class ResourceForecastManagerException extends Exception {
    private static final long serialVersionUID = 2L;

    public ResourceForecastManagerException(String message) {
        super(message);
    }

    public ResourceForecastManagerException(Throwable cause) {
        super(cause);
    }

    public ResourceForecastManagerException(String message, Throwable cause) {
        super(message, cause);
    }


}
