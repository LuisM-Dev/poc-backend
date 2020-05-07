package com.ibm.proposal.cart.adapter.in.web;

import com.ibm.proposal.cart.adapter.in.web.validator.ErrorMessage;
import com.ibm.proposal.cart.adapter.in.web.validator.WebValidator;
import com.ibm.proposal.cart.adapter.out.web.utils.RequestException;
import com.ibm.proposal.cart.application.SearchLineItemsService;
import com.ibm.proposal.cart.domain.Services;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CartControllerTest {

  private CartGetWebModel sampleCartGetWebModel;
  private CartController cartController;
  private WebValidator webValidatorMock;
  private SearchLineItemsService searchLineItemsServiceMock;

  @BeforeEach
  void setUp() {
    sampleCartGetWebModel = new CartGetWebModel();
    sampleCartGetWebModel.setProposalType("TSS Distributor Transactional");
    sampleCartGetWebModel.setProposalId("Some Proposal Id");

    cartController = new CartController();

    webValidatorMock = mock(WebValidator.class);
    searchLineItemsServiceMock = mock(SearchLineItemsService.class);
  }

  @Test
  void searchLineItemCorrectly() throws RequestException {
    List<Services> expectedServices = new ArrayList<>();

    when(webValidatorMock.validateCart(sampleCartGetWebModel)).thenReturn(new ErrorMessage());
    cartController.setWebValidator(webValidatorMock);
    when(searchLineItemsServiceMock.searchLineItems(
            sampleCartGetWebModel.getProposalId(), sampleCartGetWebModel.getProposalType()))
        .thenReturn(expectedServices);

    cartController.setSearchLineItemsUseCase(searchLineItemsServiceMock);

    Response response = cartController.searchLineItems(sampleCartGetWebModel);
    assertThat(response.getStatus()).isEqualTo(200);
    assertThat(response.getEntity()).isNotNull();
  }

  @Test
  void searchLineItemWrongInput() {
    sampleCartGetWebModel.setProposalType("Some Proposal Type");

    ErrorMessage errorMessages = new ErrorMessage();
    errorMessages.createError("proposalType", "Proposal Type is invalid");
    when(webValidatorMock.validateCart(sampleCartGetWebModel)).thenReturn(errorMessages);

    cartController.setWebValidator(webValidatorMock);
    cartController.setSearchLineItemsUseCase(searchLineItemsServiceMock);

    Response response = cartController.searchLineItems(sampleCartGetWebModel);
    assertThat(response.getStatus()).isEqualTo(400);
    ErrorMessage generatedErrorMessages = (ErrorMessage) response.getEntity();
    assertThat(generatedErrorMessages.getErrors().get("proposalType"))
        .isEqualTo("Proposal Type is invalid");
  }

  @Test
  void searchLineItemFails() throws RequestException {
    ErrorMessage errorMessages = new ErrorMessage();
    when(webValidatorMock.validateCart(sampleCartGetWebModel)).thenReturn(errorMessages);

    SearchLineItemsService searchLineItemsServiceMock = mock(SearchLineItemsService.class);
    when(searchLineItemsServiceMock.searchLineItems(
            sampleCartGetWebModel.getProposalId(), sampleCartGetWebModel.getProposalType()))
        .thenThrow(new RequestException("Authentication Expired"));

    cartController.setWebValidator(webValidatorMock);
    cartController.setSearchLineItemsUseCase(searchLineItemsServiceMock);

    Response response = cartController.searchLineItems(sampleCartGetWebModel);
    assertThat(response.getStatus()).isEqualTo(400);
    ErrorMessage generatedErrorMessages = (ErrorMessage) response.getEntity();
    assertThat(generatedErrorMessages.getErrors().get("requestError"))
        .isEqualTo("Authentication Expired");
  }
}
