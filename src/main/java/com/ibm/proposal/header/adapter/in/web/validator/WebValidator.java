package com.ibm.proposal.header.adapter.in.web.validator;

import com.ibm.proposal.header.adapter.in.web.HeaderGetWebModel;
import com.ibm.proposal.header.domain.Header;
import lombok.Setter;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.*;

public class WebValidator {

  @Setter
  @Inject
  private Validator validator;

  private ErrorMessage errorMessage;

  public WebValidator() {
    errorMessage = new ErrorMessage();
  }

  public ErrorMessage validateCreateHeaderRequest(Header header) {
    Set<ConstraintViolation<Object>> constraintViolations = validator.validate(header);
    processViolations(constraintViolations);
    return errorMessage;
  }

  public ErrorMessage validateGetRequest(HeaderGetWebModel headerGetWebModel) {
    Set<ConstraintViolation<Object>> constraintViolations = validator.validate(headerGetWebModel);
    processViolations(constraintViolations);
    return errorMessage;
  }

  private void processViolations(Set<ConstraintViolation<Object>> constraintViolations) {
    if(hasViolations(constraintViolations)){
      createRequestMessages(constraintViolations);
    }
  }

  private void createRequestMessages(Set<ConstraintViolation<Object>> violations) {
    for (ConstraintViolation<Object> headerConstraintViolation : violations) {
      if(headerConstraintViolation.getMessage().contains(":")) {
        errorMessage.createMessage(headerConstraintViolation.getMessage().split(":")[0],headerConstraintViolation.getMessage().split(":")[1]);
      } else {
        errorMessage.createMessage(
                headerConstraintViolation.getPropertyPath().toString(),
                headerConstraintViolation.getMessage());
      }
    }
  }

  private boolean hasViolations(Set<ConstraintViolation<Object>> violations) {
    return !violations.isEmpty();
  }
}
