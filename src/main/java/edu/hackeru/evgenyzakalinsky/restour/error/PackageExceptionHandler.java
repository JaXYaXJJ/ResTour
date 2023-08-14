package edu.hackeru.evgenyzakalinsky.restour.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class PackageExceptionHandler {

    @ExceptionHandler(PackageNotFoundException.class)
    public ProblemDetail handlePackageNotFoundException(PackageNotFoundException e) {

        var problemDetails = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND, e.getMessage()
        );

        problemDetails.setProperty("timestamp", LocalDateTime.now());

        return problemDetails;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        var problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST, "Validation Failed"
        );

        e.getBindingResult().getFieldErrors().forEach(fieldError -> {
            problemDetail.setProperty("field", fieldError.getField());
            problemDetail.setProperty("objectName", fieldError.getObjectName());
            problemDetail.setProperty("defaultMessage", fieldError.getDefaultMessage());
            problemDetail.setProperty("rejectedValue", fieldError.getRejectedValue());
        });

        problemDetail.setProperty("timestamp", LocalDateTime.now());

        return problemDetail;
    }

    @ExceptionHandler(PackageException.class)
    public ProblemDetail handlePackageException(PackageException e) {

        var problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()
        );

        problemDetail.setProperty("timestamp", LocalDateTime.now());

        return problemDetail;
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleException(Exception e) {

        var problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()
        );

        problemDetail.setProperty("timestamp", LocalDateTime.now());

        return problemDetail;
    }
}
