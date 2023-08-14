package edu.hackeru.evgenyzakalinsky.restour.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends PackageException {

    private final String packageName;

    public BadRequestException(String packageName) {
        super("%s was invalid".formatted(packageName));
        this.packageName = packageName;
    }

    public BadRequestException(String packageName, String message) {
        super(message);
        this.packageName = packageName;
    }
}
