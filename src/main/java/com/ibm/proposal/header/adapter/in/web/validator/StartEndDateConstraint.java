package com.ibm.proposal.header.adapter.in.web.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD, METHOD, PARAMETER, TYPE })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {StartEndDateConstraintValidator.class})
public @interface StartEndDateConstraint {

  String message() default "startDate:Start Date must be prior to End Date";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
