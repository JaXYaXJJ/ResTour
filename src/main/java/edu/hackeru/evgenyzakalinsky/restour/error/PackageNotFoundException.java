package edu.hackeru.evgenyzakalinsky.restour.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PackageNotFoundException extends RuntimeException {

    private String packageName;
    private String packageId;
    private String message;

    public PackageNotFoundException(String packageName, String packageId, String message) {

        super("%s with ID: %s, %s".formatted(packageName, packageId, message));
        this.packageName = packageName;
        this.packageId = packageId;
        this.message = message;
    }

    public PackageNotFoundException(String packageName, long packageId, String message) {

        this(packageName, String.valueOf(packageId), message);
    }

    public PackageNotFoundException(String packageName, long packageId) {

        this(packageName, String.valueOf(packageId), "is not found");
    }
}
