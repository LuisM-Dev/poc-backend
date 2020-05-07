package com.ibm.proposal.cart.adapter.out.web.assetinformation;

import com.ibm.proposal.cart.adapter.out.web.utils.Request;
import com.ibm.proposal.cart.adapter.out.web.utils.RequestException;
import com.ibm.proposal.cart.domain.Cart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AssetInformationWebAdapterTest {

  private String ASSET_INFO_REQUEST_URL = "http://localhost:9080/api/proposal/asset/information";

  private String sampleProposalId;
  private String sampleProposalType;
  private String sampleRequestBody;
  private String responseBody;
  private List<Cart.Asset> sampleCartAssets;
  @Mock private AssetInformationWebMapper assetInformationWebMapperMock;
  @Mock private Request requestMock;
  @InjectMocks private AssetInformationWebAdapter assetInformationWebAdapter;

  @BeforeEach
  void setUp() {
    sampleProposalId = "Some Proposal Id";
    sampleProposalType = "Some Proposal Type";
    sampleRequestBody = "";
    responseBody = "";
    Cart.Asset sampleCartAsset = new Cart.Asset();
    sampleCartAssets = new ArrayList<>();
    sampleCartAssets.add(sampleCartAsset);

    assetInformationWebMapperMock = mock(AssetInformationWebMapper.class);
    requestMock = mock(Request.class);
    assetInformationWebAdapter = new AssetInformationWebAdapter();
    MockitoAnnotations.initMocks(this);
  }

  @Test
  void getAssetInformationCorrectly() throws RequestException {
    when(assetInformationWebMapperMock.mapToWeb(sampleProposalId, sampleProposalType))
        .thenReturn(sampleRequestBody);
    when(assetInformationWebMapperMock.mapWebToDomain(responseBody)).thenReturn(sampleCartAssets);
    when(requestMock.postRequest(ASSET_INFO_REQUEST_URL, sampleRequestBody))
        .thenReturn(responseBody);

    List<Cart.Asset> cartAssets =
        assetInformationWebAdapter.getAssetInformation(sampleProposalId, sampleProposalType);
    assertThat(cartAssets).hasSize(1);
  }

  @Test
  void getAssetInformationException() {
    when(assetInformationWebMapperMock.mapToWeb(sampleProposalId, sampleProposalType))
        .thenReturn(sampleRequestBody);
    assertThatThrownBy(
            () -> {
              when(requestMock.postRequest(ASSET_INFO_REQUEST_URL, sampleRequestBody))
                  .thenThrow(new RequestException("Some exception"));
              assetInformationWebAdapter.getAssetInformation(sampleProposalId, sampleProposalType);
            })
        .isInstanceOf(RequestException.class)
        .hasMessage("Some exception");
  }
}
