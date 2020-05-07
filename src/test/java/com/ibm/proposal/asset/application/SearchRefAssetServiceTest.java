package com.ibm.proposal.asset.application;

import com.ibm.proposal.asset.adapter.out.persistence.RepositoryException;
import com.ibm.proposal.asset.adapter.out.web.utils.RequestException;
import com.ibm.proposal.asset.application.port.out.CreateRefAssetPort;
import com.ibm.proposal.asset.application.port.out.GetLeadCountryPort;
import com.ibm.proposal.asset.application.port.out.SearchRefAssetPort;
import com.ibm.proposal.asset.domain.Asset;
import com.ibm.proposal.asset.domain.RefAsset;
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

class SearchRefAssetServiceTest {

  private Asset sampleAsset;
  @Mock private GetLeadCountryPort getLeadCountryPortMock;
  @Mock private SearchRefAssetPort searchRefAssetPortMock;
  @Mock private CreateRefAssetPort createRefAssetPortMock;
  @InjectMocks private SearchRefAssetService searchRefAssetService;

  @BeforeEach
  void setUp() {
    sampleAsset = new Asset();
    sampleAsset.setProposalId("1");
    sampleAsset.setProposalType("Some Proposal Type");
    getLeadCountryPortMock = mock(GetLeadCountryPort.class);
    searchRefAssetPortMock = mock(SearchRefAssetPort.class);
    createRefAssetPortMock = mock(CreateRefAssetPort.class);
    searchRefAssetService = new SearchRefAssetService();
    MockitoAnnotations.initMocks(this);
  }

  @Test
  void searchRefAssetServiceShouldWorkCorrectly() throws RequestException, RepositoryException {
    RefAsset refAsset1 = new RefAsset();
    refAsset1.setInstallDate("Install Date 1");
    refAsset1.setWarrantyEndDate("Warrant End Date 1");

    RefAsset refAsset2 = new RefAsset();
    refAsset2.setInstallDate("Install Date 2");
    refAsset2.setWarrantyEndDate("Warrant End Date 2");

    List<RefAsset> sampleRefAssets = new ArrayList<>();
    sampleRefAssets.add(refAsset1);
    sampleRefAssets.add(refAsset2);

    when(getLeadCountryPortMock.getLeadCountry(
            sampleAsset.getProposalId(), sampleAsset.getProposalType()))
        .thenReturn("Some Country");

    when(searchRefAssetPortMock.searchRefAsset(sampleAsset)).thenReturn(sampleRefAssets);
    when(createRefAssetPortMock.createRefAsset(sampleAsset, sampleRefAssets)).thenReturn(true);

    List<RefAsset> actualRefAsset = searchRefAssetService.searchRefAsset(sampleAsset);
    assertThat(actualRefAsset).hasSize(2);
  }
}
