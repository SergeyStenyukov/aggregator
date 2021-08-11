package com.sirenatravel.aggregator.core.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Pattern(regexp = "^\\S{1,28}[@]\\S{2,14}[.]\\S{2,6}")
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
public @interface Email {

    String message() default "{account.email}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
