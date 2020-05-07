package com.ibm.proposal.cart.application;

import com.ibm.proposal.cart.adapter.out.persistence.CartPersistenceAdapter;
import com.ibm.proposal.cart.adapter.out.web.headerinformation.HeaderWebAdapter;
import com.ibm.proposal.cart.adapter.out.web.lineitem.LineItemWebAdapter;
import com.ibm.proposal.cart.application.port.out.GetAssetInformationPort;
import com.ibm.proposal.cart.application.port.out.GetHeaderInformationPort;
import com.ibm.proposal.cart.application.port.out.SearchLineItemsPort;
import com.ibm.proposal.cart.domain.Cart;
import com.ibm.proposal.cart.domain.Services;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SearchLineItemsServiceTest {

  private SearchLineItemsService searchLineItemsService;

  @BeforeEach
  void setUp() {
    searchLineItemsService = new SearchLineItemsService();
  }

  @Test
  void completeCartInformationWithHeaderInformationSuccessfully() {
    Cart sampleCart = new Cart();
    sampleCart.setProposalId("Some Proposal Id");
    sampleCart.setProposalType("Some Proposal Type");

    Map<String, String> sampleHeaderInfo = new HashMap<>();
    sampleHeaderInfo.put("leadCountry", "Some Lead Country");
    sampleHeaderInfo.put("quoteStartDate", "Some Quote Start Date");
    sampleHeaderInfo.put("quoteEndDate", "Some Quote End Date");
    sampleHeaderInfo.put("priceReferenceDate", "Some Price Reference Date");
    sampleHeaderInfo.put("salesChannel", "Some Sales Channel");

    Cart expectedCart = new Cart();
    expectedCart.setProposalId("Some Proposal Id");
    expectedCart.setProposalType("Some Proposal Type");
    expectedCart.setCountry("Some Lead Country");
    expectedCart.setQuoteStartDate("Some Quote Start Date");
    expectedCart.setQuoteEndDate("Some Quote End Date");
    expectedCart.setPriceReferenceDate("Some Price Reference Date");
    expectedCart.setSalesChannel("Some Sales Channel");

    searchLineItemsService.completeCartInformationWithHeaderInformation(
        sampleCart, sampleHeaderInfo);
    assertThat(sampleCart).isEqualToComparingFieldByField(expectedCart);
  }

  @Test
  void searchLineItemsCorrectly() throws Exception {
    String sampleProposalId = "Some Proposal Id";
    String sampleProposalType = "Some Proposal Type";
    Cart sampleCart = new Cart();

    List<Services> sampleServices = new ArrayList<>();

    GetHeaderInformationPort getHeaderInformationPortMock = mock(GetHeaderInformationPort.class);
    when(getHeaderInformationPortMock.getHeaderInformation(sampleProposalId, sampleProposalType))
        .thenReturn(sampleCart);

    GetAssetInformationPort getAssetInformationPortMock = mock(GetAssetInformationPort.class);
    when(getAssetInformationPortMock.getAssetInformation(sampleProposalId, sampleProposalType))
        .thenReturn(new ArrayList<>());

    SearchLineItemsPort searchLineItemsPortMock = mock(SearchLineItemsPort.class);
    when(searchLineItemsPortMock.searchLineItems(sampleCart)).thenReturn(sampleServices);

    searchLineItemsService.setGetHeaderInformationPort(getHeaderInformationPortMock);
    searchLineItemsService.setGetAssetInformationPort(getAssetInformationPortMock);
    searchLineItemsService.setSearchLineItemsPort(searchLineItemsPortMock);

    List<Services> services =
        searchLineItemsService.searchLineItems(sampleProposalId, sampleProposalType);
    assertThat(services).isNotNull();
  }
}
