package com.ibm.proposal.header.adapter.in.web;

import com.ibm.proposal.header.adapter.in.web.validator.ErrorMessage;
import com.ibm.proposal.header.adapter.in.web.validator.WebValidator;
import com.ibm.proposal.header.adapter.out.persistence.RepositoryException;
import com.ibm.proposal.header.application.port.in.CreateHeaderUseCase;
import com.ibm.proposal.header.application.port.in.GetHeaderInformationUseCase;
import com.ibm.proposal.header.application.port.in.GetHeaderUseCase;
import com.ibm.proposal.header.domain.Header;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class HeaderControllerTest {

  private Header sampleHeader;
  private HeaderGetWebModel sampleHeaderGetWebModel;
  @Mock private WebValidator webValidatorMock;
  @Mock private CreateHeaderUseCase createHeaderUseCaseMock;
  @Mock private GetHeaderInformationUseCase getHeaderInformationUseCaseMock;
  @Mock private GetHeaderUseCase getHeaderUseCaseMock;
  @InjectMocks private HeaderController headerController;

  @BeforeEach
  void setUp() {
    sampleHeader = new Header();
    sampleHeader.setId("Some Id");
    sampleHeader.setType("Some Type");

    sampleHeaderGetWebModel = new HeaderGetWebModel();
    sampleHeaderGetWebModel.setProposalId("Some Id");
    sampleHeaderGetWebModel.setProposalType("Some Type");

    webValidatorMock = mock(WebValidator.class);
    createHeaderUseCaseMock = mock(CreateHeaderUseCase.class);
    getHeaderInformationUseCaseMock = mock(GetHeaderInformationUseCase.class);
    getHeaderUseCaseMock = mock(GetHeaderUseCase.class);
    headerController = new HeaderController();
    MockitoAnnotations.initMocks(this);
  }

  @Test
  void createHeaderControllerSuccessfully() throws RepositoryException {
    when(webValidatorMock.validateCreateHeaderRequest(sampleHeader)).thenReturn(new ErrorMessage());
    when(createHeaderUseCaseMock.createProposal(sampleHeader)).thenReturn(true);

    Response response = headerController.createHeaderProposal(sampleHeader);
    assertThat(response.getStatus()).isEqualTo(200);
  }

  @Test
  void createHeaderFailValidator() {
    ErrorMessage errorMessage = new ErrorMessage();
    errorMessage.createMessage("sampleError", "sampleErrorMessage");

    when(webValidatorMock.validateCreateHeaderRequest(sampleHeader)).thenReturn(errorMessage);

    Response response = headerController.createHeaderProposal(sampleHeader);
    assertThat(response.getStatus()).isEqualTo(400);
    assertThat(((ErrorMessage) response.getEntity()).getErrors().get("sampleError"))
        .isEqualTo("sampleErrorMessage");
  }

  @Test
  void createHeaderControllerThrowsException() throws RepositoryException {
    when(webValidatorMock.validateCreateHeaderRequest(sampleHeader)).thenReturn(new ErrorMessage());
    doThrow(new RepositoryException("Failed to create header"))
        .when(createHeaderUseCaseMock)
        .createProposal(sampleHeader);

    Response response = headerController.createHeaderProposal(sampleHeader);
    assertThat(response.getStatus()).isEqualTo(400);
    assertThat(((ErrorMessage) response.getEntity()).getErrors().get("repository"))
        .isEqualTo("Failed to create header");
  }

  @Test
  void getHeaderInformationCorrectly() throws RepositoryException {
    Map<String, String> expectedHeaderInformation = new HashMap<>();
    expectedHeaderInformation.put("leadCountry", "Some Lead Country");
    expectedHeaderInformation.put("quoteStartDate", "Some Quote Start Date");
    expectedHeaderInformation.put("quoteEndDate", "Some Quote End Date");
    expectedHeaderInformation.put("priceReferenceDate", "Some Price Reference Date");
    expectedHeaderInformation.put("salesChannel", "Some Sales Channel");

    when(webValidatorMock.validateGetRequest(sampleHeaderGetWebModel))
        .thenReturn(new ErrorMessage());
    when(getHeaderInformationUseCaseMock.getHeaderInformation(
            sampleHeaderGetWebModel.getProposalId(), sampleHeaderGetWebModel.getProposalType()))
        .thenReturn(expectedHeaderInformation);

    Response response = headerController.getHeaderInformation(sampleHeaderGetWebModel);
    assertThat(response.getStatus()).isEqualTo(200);
  }

  @Test
  void getHeaderInformationFailValidator() {
    ErrorMessage errorMessage = new ErrorMessage();
    errorMessage.createMessage("sampleError", "sampleErrorMessage");

    when(webValidatorMock.validateGetRequest(sampleHeaderGetWebModel)).thenReturn(errorMessage);

    Response response = headerController.getHeaderInformation(sampleHeaderGetWebModel);
    assertThat(response.getStatus()).isEqualTo(400);
    assertThat(((ErrorMessage) response.getEntity()).getErrors().get("sampleError"))
        .isEqualTo("sampleErrorMessage");
  }

  @Test
  void getHeaderInformationThrowsException() throws RepositoryException {
    when(webValidatorMock.validateGetRequest(sampleHeaderGetWebModel))
        .thenReturn(new ErrorMessage());
    when(getHeaderInformationUseCaseMock.getHeaderInformation(
            sampleHeaderGetWebModel.getProposalId(), sampleHeaderGetWebModel.getProposalType()))
        .thenThrow(new RepositoryException("Failed to find header"));

    Response response = headerController.getHeaderInformation(sampleHeaderGetWebModel);
    assertThat(response.getStatus()).isEqualTo(400);
    assertThat(((ErrorMessage) response.getEntity()).getErrors().get("repository"))
        .isEqualTo("Failed to find header");
  }

  @Test
  void getHeaderCorrectly() throws RepositoryException {
    when(webValidatorMock.validateGetRequest(sampleHeaderGetWebModel))
        .thenReturn(new ErrorMessage());
    when(getHeaderUseCaseMock.getHeader(
            sampleHeaderGetWebModel.getProposalId(), sampleHeaderGetWebModel.getProposalType()))
        .thenReturn(new Header());

    Response response = headerController.getHeaderProposal(sampleHeaderGetWebModel);
    assertThat(response.getStatus()).isEqualTo(200);
  }

  @Test
  void getHeaderFailValidator() {
    ErrorMessage errorMessage = new ErrorMessage();
    errorMessage.createMessage("sampleError", "sampleErrorMessage");

    when(webValidatorMock.validateGetRequest(sampleHeaderGetWebModel)).thenReturn(errorMessage);

    Response response = headerController.getHeaderProposal(sampleHeaderGetWebModel);
    assertThat(response.getStatus()).isEqualTo(400);
    assertThat(((ErrorMessage) response.getEntity()).getErrors().get("sampleError"))
        .isEqualTo("sampleErrorMessage");
  }

  @Test
  void getHeaderThrowsException() throws RepositoryException {
    when(webValidatorMock.validateGetRequest(sampleHeaderGetWebModel))
        .thenReturn(new ErrorMessage());
    when(getHeaderUseCaseMock.getHeader(
            sampleHeaderGetWebModel.getProposalId(), sampleHeaderGetWebModel.getProposalType()))
        .thenThrow(new RepositoryException("Some Exception"));

    Response response = headerController.getHeaderProposal(sampleHeaderGetWebModel);
    assertThat(response.getStatus()).isEqualTo(400);
    assertThat(((ErrorMessage) response.getEntity()).getErrors().get("repository"))
        .isEqualTo("Some Exception");
  }

  @SuppressWarnings("unchecked")
  @Test
  void getAllHeadersCorrectly() throws RepositoryException {
    List<Header> sampleHeaders = new ArrayList<>();
    sampleHeaders.add(new Header());
    sampleHeaders.add(new Header());

    when(getHeaderUseCaseMock.getHeaders()).thenReturn(sampleHeaders);

    Response response = headerController.getAllHeaderProposal();
    assertThat(response.getStatus()).isEqualTo(200);
    assertThat(((List<Header>) response.getEntity()).size()).isEqualTo(2);
  }

  @Test
  void getAllHeadersThrowsException() throws RepositoryException {
    when(getHeaderUseCaseMock.getHeaders()).thenThrow(new RepositoryException("Some Exception"));

    Response response = headerController.getAllHeaderProposal();
    assertThat(response.getStatus()).isEqualTo(400);
    assertThat(((ErrorMessage) response.getEntity()).getErrors().get("repository"))
        .isEqualTo("Some Exception");
  }
}
