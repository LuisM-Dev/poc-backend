package com.ibm.proposal.cart.adapter.in.web;

import com.ibm.proposal.cart.adapter.in.web.validator.ErrorMessage;
import com.ibm.proposal.cart.adapter.in.web.validator.WebValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ValidatorTest {

  private CartGetWebModel sampleCartGetWebModel;
  private WebValidator webValidator;

  @BeforeEach
  void setUp() {
    sampleCartGetWebModel = new CartGetWebModel();
    sampleCartGetWebModel.setProposalType("Some Proposal Type");
    sampleCartGetWebModel.setProposalId("Some Proposal Id");

    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();

    webValidator = new WebValidator();
    webValidator.setValidator(validator);
  }

  @Test
  void cartInformationProvidedInCorrectly() {
    ErrorMessage errorMessages = webValidator.validateCart(sampleCartGetWebModel);
    assertThat(errorMessages.getErrors()).isNotEmpty();
    assertThat(errorMessages.getErrors().get("proposalType"))
        .isEqualTo("Proposal Type is invalid");
  }

  @Test
  void cartInformationProvidedCorrectly() {
    sampleCartGetWebModel.setProposalType("TSS Distributor Transactional");
    ErrorMessage errorMessages = webValidator.validateCart(sampleCartGetWebModel);
    assertThat(errorMessages.getErrors()).isEmpty();
  }
}
