package com.ibm.proposal.header.adapter.in.web.validator;

import com.ibm.proposal.header.domain.Header;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StartEndDateConstraintValidator implements ConstraintValidator<StartEndDateConstraint, Header> {

    @Override
    public boolean isValid(Header header, ConstraintValidatorContext constraintValidatorContext) {
        if(header.getStartDate() != null && header.getEndDate() != null) {
            return header.getStartDate().compareTo(header.getEndDate()) < 0;
        }
        return false;
    }

    @Override
    public void initialize(StartEndDateConstraint constraintAnnotation) {
    }
}
