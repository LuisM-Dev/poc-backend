package com.ibm.proposal.asset.adapter.in.web;

import com.ibm.proposal.asset.adapter.in.web.validator.ErrorMessage;
import com.ibm.proposal.asset.adapter.in.web.validator.WebValidator;
import com.ibm.proposal.asset.adapter.out.persistence.RepositoryException;
import com.ibm.proposal.asset.adapter.out.web.utils.RequestException;
import com.ibm.proposal.asset.application.port.in.GetRefAssetUseCase;
import com.ibm.proposal.asset.application.port.in.SearchRefAssetUseCase;
import com.ibm.proposal.asset.domain.Asset;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.ws.rs.core.Response;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AssetControllerTest {

  private AssetWebModel sampleAssetWebModel;
  private Asset sampleAsset;
  @Mock private WebValidator webValidatorMock;
  @Mock AssetWebMapper assetWebMapperMock;
  @Mock private SearchRefAssetUseCase searchRefAssetUseCaseMock;
  @Mock private GetRefAssetUseCase getRefAssetUseCaseMock;
  @InjectMocks private AssetController assetController;

  @BeforeEach
  void setUp() {
    sampleAssetWebModel = new AssetWebModel();
    sampleAssetWebModel.setProposalType("Some Proposal Type");
    sampleAsset = new Asset();
    sampleAsset.setProposalType("Some Proposal Type");
    sampleAsset.setRecordType("ibmcTSSDirectTransactionalQuote");

    webValidatorMock = mock(WebValidator.class);
    searchRefAssetUseCaseMock = mock(SearchRefAssetUseCase.class);
    assetWebMapperMock = mock(AssetWebMapper.class);
    getRefAssetUseCaseMock = mock(GetRefAssetUseCase.class);
    assetController = new AssetController();
    MockitoAnnotations.initMocks(this);
  }

  @Test
  void searchRefAssetSuccessfully() throws RequestException, RepositoryException {
    when(webValidatorMock.validateSearchRefAsset(sampleAssetWebModel))
        .thenReturn(new ErrorMessage());
    when(assetWebMapperMock.mapToEntityDomain(sampleAssetWebModel)).thenReturn(sampleAsset);
    when(searchRefAssetUseCaseMock.searchRefAsset(sampleAsset)).thenReturn(new ArrayList<>());

    Response response = assetController.searchAsset(sampleAssetWebModel);
    assertThat(response.getStatus()).isEqualTo(200);
  }

  @Test
  void searchRefAssetIncorrectParameters() {
    ErrorMessage errorMessage = new ErrorMessage();
    errorMessage.createError("someError", "some error message");

    when(webValidatorMock.validateSearchRefAsset(sampleAssetWebModel)).thenReturn(errorMessage);

    Response response = assetController.searchAsset(sampleAssetWebModel);
    assertThat(response.getStatus()).isEqualTo(400);
    assertThat(((ErrorMessage) response.getEntity()).getErrors().get("someError"))
        .isEqualTo("some error message");
  }

  @Test
  void searchRefAssetLeadCountryException() throws RequestException, RepositoryException {
    when(webValidatorMock.validateSearchRefAsset(sampleAssetWebModel))
        .thenReturn(new ErrorMessage());
    when(assetWebMapperMock.mapToEntityDomain(sampleAssetWebModel)).thenReturn(sampleAsset);
    when(searchRefAssetUseCaseMock.searchRefAsset(sampleAsset))
        .thenThrow(new RequestException("Some exception"));

    Response response = assetController.searchAsset(sampleAssetWebModel);
    assertThat(response.getStatus()).isEqualTo(400);
    assertThat(((ErrorMessage) response.getEntity()).getErrors().get("request"))
        .isEqualTo("Some exception");
  }

  @Test
  void getRefAssetSuccessfully() {
    AssetGetWebModel assetGetWebModel = new AssetGetWebModel();

    when(webValidatorMock.validateGetRefAsset(assetGetWebModel)).thenReturn(new ErrorMessage());
    when(getRefAssetUseCaseMock.getRefAsset("Some Id")).thenReturn(new ArrayList<>());

    Response response = assetController.getAsset(assetGetWebModel);
    assertThat(response.getStatus()).isEqualTo(200);
  }
}
