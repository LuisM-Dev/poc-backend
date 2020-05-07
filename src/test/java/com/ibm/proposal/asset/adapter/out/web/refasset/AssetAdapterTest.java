package com.ibm.proposal.asset.adapter.out.web.refasset;

import com.ibm.proposal.asset.adapter.out.web.utils.Request;
import com.ibm.proposal.asset.adapter.out.web.utils.RequestException;
import com.ibm.proposal.asset.domain.Asset;
import com.ibm.proposal.asset.domain.RefAsset;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AssetAdapterTest {

  private final String SEARCH_REF_ASSET_REQUEST_URL =
      "https://ra-api-uat.quest-tss-435a25d3f409a5d1651084a621f22ab6-0001.us-south.containers.appdomain.cloud/api/search/referenceasset";
  private Asset sampleAsset;
  @Mock
  private Request requestMock;
  @Mock
  private RefAssetMapper refAssetMapperMock;
  @InjectMocks
  private RefAssetAdapter refAssetAdapter;

  @BeforeEach
  void setUp() {
    sampleAsset = new Asset();
    sampleAsset.setProposalId("1");
    sampleAsset.setCountryCode("Some Country");
    sampleAsset.setAssetSerialNumber("Some Serial");
    sampleAsset.setInstalledCustomerNumber("Some Customer Number");
    sampleAsset.setModel("Some Model");
    sampleAsset.setType("Some Type");
    sampleAsset.setSoldTo("Some Sold To");
    requestMock = mock(Request.class);
    refAssetMapperMock = mock(RefAssetMapper.class);
    refAssetAdapter = new RefAssetAdapter();
    MockitoAnnotations.initMocks(this);
  }

  @Test
  void searchRefAssetIsPerformedCorrectly() throws RequestException {
    String sampleRequestBody = "";
    String sampleResponseBody = "{ hardware: []}";
    List<RefAsset> sampleRefAssets = new ArrayList<>();
    sampleRefAssets.add(new RefAsset());
    sampleRefAssets.add(new RefAsset());

    when(requestMock.postRequest(SEARCH_REF_ASSET_REQUEST_URL, sampleRequestBody))
        .thenReturn(sampleResponseBody);
    when(refAssetMapperMock.mapToRequestBody(sampleAsset)).thenReturn(sampleRequestBody);
    when(refAssetMapperMock.mapToRefAsset(sampleResponseBody)).thenReturn(sampleRefAssets);

    List<RefAsset> actualRefAssets = refAssetAdapter.searchRefAsset(sampleAsset);
    assertThat(actualRefAssets).hasSize(2);
  }

  @Test
  void searchRefAssetThrowsException() {
    String sampleRequestBody = "";

    when(refAssetMapperMock.mapToRequestBody(sampleAsset)).thenReturn(sampleRequestBody);

    assertThatThrownBy(() -> {
      when(requestMock.postRequest(SEARCH_REF_ASSET_REQUEST_URL, sampleRequestBody)).thenThrow(new RequestException("Some Exception"));
      refAssetAdapter.searchRefAsset(sampleAsset);
    }).isInstanceOf(RequestException.class).hasMessage("Some Exception");
  }
}
