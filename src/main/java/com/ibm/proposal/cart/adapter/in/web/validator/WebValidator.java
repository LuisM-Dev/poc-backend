package com.ibm.proposal.cart.adapter.in.web.validator;

import com.ibm.proposal.cart.adapter.in.web.CartGetWebModel;
import lombok.Setter;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

public class WebValidator {

    @Setter
    @Inject
    private Validator validator;

    @Inject
    private ErrorMessage errorMessage;

    public WebValidator() {
        errorMessage = new ErrorMessage();
    }

    public ErrorMessage validateCart(CartGetWebModel cartGetWebModel) {
        Set<ConstraintViolation<Object>> violations = validator.validate(cartGetWebModel);
        verifyViolations(violations);
        return errorMessage;
    }

    private void verifyViolations(Set<ConstraintViolation<Object>> violations) {
        if(hasViolations(violations)){
            createCartInfoViolationMessages(violations);
        }
    }

    private void createCartInfoViolationMessages(Set<ConstraintViolation<Object>> violations) {
        for (ConstraintViolation<Object> headerConstraintViolation : violations) {
            errorMessage.createError(
                    headerConstraintViolation.getPropertyPath().toString(),
                    headerConstraintViolation.getMessage());
        }
    }

    private boolean hasViolations(Set<ConstraintViolation<Object>> violations) {
        return !violations.isEmpty();
    }
}
