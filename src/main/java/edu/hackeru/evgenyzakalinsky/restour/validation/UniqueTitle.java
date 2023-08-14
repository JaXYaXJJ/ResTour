package edu.hackeru.evgenyzakalinsky.restour.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME) //we need to check in runtime
@Constraint(validatedBy = {UniqueTitleValidator.class})
public @interface UniqueTitle {
    String message() default "Post Title must be unique";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default { };
}
