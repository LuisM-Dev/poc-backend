package com.ibm.proposal.header.application;

import com.ibm.proposal.header.adapter.out.persistence.RepositoryException;
import com.ibm.proposal.header.application.port.out.GetHeaderPort;
import com.ibm.proposal.header.domain.Header;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GetHeaderInformationServiceTest {

    @Mock
    private GetHeaderPort getHeaderPortMock;
    @InjectMocks private GetHeaderInformationService getHeaderInformationService;


  @BeforeEach
  void setUp() {
      getHeaderPortMock = mock(GetHeaderPort.class);
      getHeaderInformationService = new GetHeaderInformationService();
      MockitoAnnotations.initMocks(this);
  }

    @Test
    void getHeaderInformationCorrectly() throws RepositoryException {
        String sampleProposalId = "Some Proposal Id";
        String sampleProposalType = "Some Proposal Type";

        Header sampleHeader = new Header();
        sampleHeader.setLeadCountry("Some Lead Country");
        sampleHeader.setStartDate(LocalDate.parse("2020-01-01"));
        sampleHeader.setEndDate(LocalDate.parse("2020-01-01"));
        sampleHeader.setPricingDate(LocalDate.parse("2020-01-01"));
        sampleHeader.setSalesChannel("J");

        Map<String, String> expectedHeaderInformation = new HashMap<>();
        expectedHeaderInformation.put("leadCountry", "Some Lead Country");
        expectedHeaderInformation.put("startDate", "2020-01-01");
        expectedHeaderInformation.put("endDate", "2020-01-01");
        expectedHeaderInformation.put("pricingDate", "2020-01-01");
        expectedHeaderInformation.put("salesChannel", "J");

        when(getHeaderPortMock.getHeader(sampleProposalId, sampleProposalType)).thenReturn(sampleHeader);

        Map<String, String> actualHeaderInformation =  getHeaderInformationService.getHeaderInformation(sampleProposalId,sampleProposalType);

        assertThat(actualHeaderInformation).isEqualToComparingFieldByField(expectedHeaderInformation);
    }
}
