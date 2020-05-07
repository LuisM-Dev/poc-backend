package com.ibm.proposal.asset.adapter.out.web.proposalleadcountry;

import com.ibm.proposal.asset.adapter.out.web.utils.Request;
import com.ibm.proposal.asset.adapter.out.web.utils.RequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LeadCountryAdapterTest {
  private static final String LEAD_COUNTRY_REQUEST_URL =
      "http://localhost:9080/api/proposal/header/information";
  @Mock
  private Request requestMock;
  @Mock
  private LeadCountryMapper leadCountryMapperMock;
  @InjectMocks
  private LeadCountryAdapter leadCountryAdapter;

  @BeforeEach
  void setUp() {
    leadCountryAdapter = new LeadCountryAdapter();
    requestMock = mock(Request.class);
    leadCountryMapperMock = mock(LeadCountryMapper.class);
    MockitoAnnotations.initMocks(this);
  }

  @Test
  void shouldRetrieveLeadCountryCorrectly() throws RequestException {
    String sampleResponse = "{\"leadCountry\":\"Some country\"}";
    String sampleMapToRequestBody = "{\"proposalId\":\"1\",\"proposalType\":\"proposal type\"}";
    String sampleMapToLeadCountry = "Some country";

    when(requestMock.postRequest(LEAD_COUNTRY_REQUEST_URL, sampleMapToRequestBody))
        .thenReturn(sampleResponse);
    when(leadCountryMapperMock.mapToRequestBody("1", "proposal type"))
        .thenReturn(sampleMapToRequestBody);
    when(leadCountryMapperMock.mapToLeadCountry(sampleResponse))
        .thenReturn(sampleMapToLeadCountry);

    String country = leadCountryAdapter.getLeadCountry("1", "proposal type");
    assertThat(country).isEqualTo("Some country");
  }

  @Test
  void retrieveLeadCountryError() {
    String sampleMapToRequestBody = "{\"proposalId\":\"1\",\"proposalType\":\"proposal type\"}";

    when(leadCountryMapperMock.mapToRequestBody("1", "proposal type"))
            .thenReturn(sampleMapToRequestBody);

    assertThatThrownBy(() -> {
      when(requestMock.postRequest(LEAD_COUNTRY_REQUEST_URL, sampleMapToRequestBody))
              .thenThrow(new RequestException("Some Exception"));
      leadCountryAdapter.getLeadCountry("1", "proposal type");
    }).isInstanceOf(RequestException.class).hasMessage("Some Exception");
  }
}
