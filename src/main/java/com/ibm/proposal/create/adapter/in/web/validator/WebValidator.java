package com.ibm.proposal.create.adapter.in.web.validator;

import com.ibm.proposal.create.adapter.in.web.CreateWebModel;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

public class WebValidator {

  @Inject private Validator validator;

  public CreateErrorMessage validateProposalType(CreateWebModel createWebModel) {
    CreateErrorMessage error = new CreateErrorMessage();
    Set<ConstraintViolation<CreateWebModel>> violations = validator.validate(createWebModel);
    if (hasViolations(violations)) {
      createViolationsMessages(error, violations);
    }
    return error;
  }

  private void createViolationsMessages(CreateErrorMessage error, Set<ConstraintViolation<CreateWebModel>> violations) {
    violations.forEach(violation -> error.createErrorMessage(violation.getPropertyPath().toString(), violation.getMessage()));
  }

  private boolean hasViolations(Set<ConstraintViolation<CreateWebModel>> violations) {
    return !violations.isEmpty();
  }
}
