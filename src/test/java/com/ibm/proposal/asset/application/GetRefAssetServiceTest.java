package com.ibm.proposal.asset.application;

import com.ibm.proposal.asset.application.port.out.GetRefAssetPort;
import com.ibm.proposal.asset.domain.RefAsset;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GetRefAssetServiceTest {

  private String sampleId;
  private List<RefAsset> expectedRefAssets;
  @Mock private GetRefAssetPort getRefAssetPortMock;
  @InjectMocks private GetRefAssetService getRefAssetService;

  @BeforeEach
  void setUp() {
    sampleId = "";
    expectedRefAssets = new ArrayList<>();
    getRefAssetPortMock = mock(GetRefAssetPort.class);
    getRefAssetService = new GetRefAssetService();
    MockitoAnnotations.initMocks(this);
  }

  @Test
  void getRefAssetSuccessfully() {
    expectedRefAssets.add(new RefAsset());

    when(getRefAssetPortMock.getRefAsset(sampleId)).thenReturn(expectedRefAssets);

    List<RefAsset> refAssets = getRefAssetService.getRefAsset(sampleId);
    assertThat(refAssets).hasSize(1);
  }

  @Test
  void getRefAssetNonExistent() {
    when(getRefAssetPortMock.getRefAsset(sampleId)).thenReturn(expectedRefAssets);

    List<RefAsset> refAssets = getRefAssetService.getRefAsset(sampleId);
    assertThat(refAssets).hasSize(0);
  }
}
