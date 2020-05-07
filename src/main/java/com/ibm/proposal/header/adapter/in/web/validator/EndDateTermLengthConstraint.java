package com.ibm.proposal.header.adapter.in.web.validator;

import javax.validation.Constraint;
import javax.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD, METHOD, PARAMETER, TYPE })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {EndDateTermLengthConstraintValidator.class})
public @interface EndDateTermLengthConstraint {

    String message() default "endDate:End Date must be Start Date plus Term Length";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
