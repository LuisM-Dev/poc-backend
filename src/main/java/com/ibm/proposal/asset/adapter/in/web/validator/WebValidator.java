package com.ibm.proposal.asset.adapter.in.web.validator;

import com.ibm.proposal.asset.adapter.in.web.AssetGetWebModel;
import com.ibm.proposal.asset.adapter.in.web.AssetWebModel;
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
    private ErrorMessage errorMessages;

    public WebValidator() {
        errorMessages = new ErrorMessage();
    }

    public ErrorMessage validateSearchRefAsset(AssetWebModel assetWebModel) {
        Set<ConstraintViolation<Object>> violations = validator.validate(assetWebModel);
        verifyViolations(violations);
        return errorMessages;
    }

    public ErrorMessage validateGetRefAsset(AssetGetWebModel assetGetWebModel) {
        Set<ConstraintViolation<Object>> violations = validator.validate(assetGetWebModel);
        verifyViolations(violations);
        return errorMessages;
    }

    private void verifyViolations(Set<ConstraintViolation<Object>> violations) {
        if (!violations.isEmpty()) {
            createViolationMessages(violations);
        }
    }

    private void createViolationMessages(Set<ConstraintViolation<Object>> violations) {
        for (ConstraintViolation<Object> headerConstraintViolation : violations) {
            errorMessages.createError(
                    headerConstraintViolation.getPropertyPath().toString(),
                    headerConstraintViolation.getMessage());
        }
    }


}
