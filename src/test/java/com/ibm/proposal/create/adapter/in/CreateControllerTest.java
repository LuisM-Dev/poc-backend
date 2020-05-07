package com.ibm.proposal.create.adapter.in;

import com.ibm.proposal.create.adapter.in.web.CreateController;
import com.ibm.proposal.create.adapter.in.web.CreateWebModel;
import com.ibm.proposal.create.adapter.in.web.validator.CreateErrorMessage;
import com.ibm.proposal.create.adapter.in.web.validator.WebValidator;
import com.ibm.proposal.create.adapter.out.persistence.RepositoryException;
import com.ibm.proposal.create.application.port.in.CreateNewProposalUseCase;
import com.ibm.proposal.create.domain.Proposal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.ws.rs.core.Response;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CreateControllerTest {

  private CreateWebModel sampleCreateWebModel;
  @Mock
  private WebValidator webValidatorMock;
  @Mock
  private CreateNewProposalUseCase createNewProposalUseCaseMock;
  @InjectMocks
  private CreateController createController;

  @BeforeEach
  void setUp() {
    sampleCreateWebModel = new CreateWebModel();
    createController = new CreateController();

    webValidatorMock = mock(WebValidator.class);
    createNewProposalUseCaseMock = mock(CreateNewProposalUseCase.class);
      MockitoAnnotations.initMocks(this);
  }

  @Test
  void createProposalInvalidType() {
    CreateErrorMessage sampleCreateErrorMessage = new CreateErrorMessage();
    sampleCreateErrorMessage.createErrorMessage("Proposal Type", "Invalid Proposal Type");

    when(webValidatorMock.validateProposalType(sampleCreateWebModel))
        .thenReturn(sampleCreateErrorMessage);

    Response response = createController.createNewProposal(sampleCreateWebModel);
    assertThat(response.getStatus()).isEqualTo(400);
  }

  @Test
  void createProposalCorrectly() throws RepositoryException {
    when(webValidatorMock.validateProposalType(sampleCreateWebModel))
        .thenReturn(new CreateErrorMessage());
    when(createNewProposalUseCaseMock.createNewProposal("")).thenReturn(new Proposal("Some Type"));

    Response response = createController.createNewProposal(sampleCreateWebModel);
    assertThat(response.getStatus()).isEqualTo(201);
  }

  @Test
  void createProposalInCorrectly() throws RepositoryException {
    sampleCreateWebModel.setProposalType("Some Type");

    when(webValidatorMock.validateProposalType(sampleCreateWebModel))
        .thenReturn(new CreateErrorMessage());
    when(createNewProposalUseCaseMock.createNewProposal("Some Type"))
        .thenThrow(new RepositoryException("failed create proposal"));

    Response response = createController.createNewProposal(sampleCreateWebModel);
    assertThat(response.getStatus()).isEqualTo(503);
  }
}
