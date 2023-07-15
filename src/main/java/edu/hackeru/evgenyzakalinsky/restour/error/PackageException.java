package edu.hackeru.evgenyzakalinsky.restour.error;

public class PackageException extends RuntimeException {
    public PackageException() {
    }

    public PackageException(String message) {
        super(message);
    }

    public PackageException(String message, Throwable cause) {
        super(message, cause);
    }

    public PackageException(Throwable cause) {
        super(cause);
    }

    public PackageException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
