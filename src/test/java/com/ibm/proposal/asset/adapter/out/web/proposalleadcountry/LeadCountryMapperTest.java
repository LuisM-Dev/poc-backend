package com.ibm.proposal.asset.adapter.out.web.proposalleadcountry;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class LeadCountryMapperTest {
  private LeadCountryMapper leadCountryMapper;

  @BeforeEach
  void setUp() {
    leadCountryMapper = new LeadCountryMapper();
  }

  @Test
  void mapCorrectlyToRequestBody() {
    String expected = "{\"proposalId\":\"1\",\"proposalType\":\"proposal type\"}";
    String actual = leadCountryMapper.mapToRequestBody("1", "proposal type");
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void mapCorrectlyToLeadCountry() {
    String leadCountryResponse = "{\"leadCountry\":\"Some country\"}";
    String actual = leadCountryMapper.mapToLeadCountry(leadCountryResponse);
    assertThat(actual).isEqualTo("Some country");
  }

  @Test
  void incorrectMapToLeadCountry() {
    String leadCountryResponse = "{\"country\":\"Some country\"}";
    String actual = leadCountryMapper.mapToLeadCountry(leadCountryResponse);
    assertThat(actual).isNull();
  }
}
