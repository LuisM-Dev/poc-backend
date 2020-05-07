package com.ibm.proposal.header.adapter.in.web.validator;

import com.ibm.proposal.header.domain.Header;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class EndDateTermLengthConstraintValidator implements ConstraintValidator<EndDateTermLengthConstraint, Header> {

    @Override
    public boolean isValid(Header header, ConstraintValidatorContext constraintValidatorContext) {
        if(header.getStartDate() != null && header.getEndDate() != null && header.getTermLengthMonths() != null) {
            LocalDate endDateCalculated = header.getStartDate().plusMonths(Integer.parseInt(header.getTermLengthMonths()));
            return endDateCalculated.compareTo(header.getEndDate()) == 0;
        }
        return false;
    }

    @Override
    public void initialize(EndDateTermLengthConstraint constraintAnnotation) {
    }
}
