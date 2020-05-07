package com.ibm.proposal.asset.adapter.in.web;

import com.ibm.proposal.asset.adapter.in.web.validator.ErrorMessage;
import com.ibm.proposal.asset.adapter.in.web.validator.WebValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static org.assertj.core.api.Assertions.assertThat;

class ValidatorTest {

  private AssetWebModel sampleAssetWeb;
  private WebValidator webValidator;

  @BeforeEach
  void setUp() {
    sampleAssetWeb = new AssetWebModel();
    sampleAssetWeb.setProposalId("1");
    sampleAssetWeb.setProposalType("TSS Distributor Transactional");
    sampleAssetWeb.setType("Some Type");
    sampleAssetWeb.setModel("Some Model");
    sampleAssetWeb.setInstalledCustomerNumber("Some Customer Number");
    sampleAssetWeb.setAssetSerialNumber("Some Serial Number");

    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();

    webValidator = new WebValidator();
    webValidator.setValidator(validator);
  }

  @Test
  void assetInformationProvidedCorrectly() {
    ErrorMessage errorMessages = webValidator.validateSearchRefAsset(sampleAssetWeb);
    assertThat(errorMessages.getErrors()).isEmpty();
  }

  @Test
  void assetInformationTypeProvidedIncorrectly() {
    sampleAssetWeb.setProposalType("Some Type");
    ErrorMessage errorMessages = webValidator.validateSearchRefAsset(sampleAssetWeb);
    assertThat(errorMessages.getErrors()).isNotEmpty();
    assertThat(errorMessages.getErrors().get("proposalType"))
        .isEqualTo("Proposal Type is invalid");
  }

  @Test
  void getAssetInformationCorrectly() {
    AssetGetWebModel assetGetWebModel = new AssetGetWebModel();
    assetGetWebModel.setProposalId("Some Proposal Id");
    ErrorMessage errorMessages = webValidator.validateGetRefAsset(assetGetWebModel);
    assertThat(errorMessages.getErrors()).isEmpty();
  }

  @Test
  void getAssetInformationInCorrectly() {
    AssetGetWebModel assetGetWebModel = new AssetGetWebModel();
    ErrorMessage errorMessages = webValidator.validateGetRefAsset(assetGetWebModel);
    assertThat(errorMessages.getErrors()).isNotEmpty();
    assertThat(errorMessages.getErrors().get("proposalId"))
        .isEqualTo("ID cannot be empty");
  }
}
