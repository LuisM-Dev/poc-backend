package com.ibm.proposal.cart.adapter.out.web.headerinformation;

import com.ibm.proposal.cart.domain.Cart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class HeaderWebMapperTest {

  private HeaderWebMapper headerWebMapper;

  @BeforeEach
  void setUp() {
    headerWebMapper = new HeaderWebMapper();
  }

  @Test
  void mapEntityToWebCorrectly() {
    String proposalId = "Some Proposal Id";
    String proposalType = "Some Proposal Type";

    String expectedWebRequestBody = "{\"proposalId\":\"Some Proposal Id\",\"proposalType\":\"Some Proposal Type\"}";

    String actualWebRequestBody = headerWebMapper.mapEntityToWeb(proposalId, proposalType);
    assertThat(actualWebRequestBody).isEqualTo(expectedWebRequestBody);
  }

  @Test
  void mapWebToEntityCorrectly() {
    String sampleResponseBody =
        "{"
            + "\"leadCountry\":\"Some Lead Country\","
            + "\"startDate\":\"Some Quote Start Date\","
            + "\"endDate\":\"Some Quote End Date\","
            + "\"pricingDate\":\"Some Price Reference Date\","
            + "\"salesChannel\":\"Some Sales Channel\""
            + "}";

    Cart expectedCart = new Cart();
    expectedCart.setCountry("Some Lead Country");
    expectedCart.setQuoteStartDate("Some Quote Start Date");
    expectedCart.setQuoteEndDate("Some Quote End Date");
    expectedCart.setPriceReferenceDate("Some Price Reference Date");
    expectedCart.setSalesChannel( "Some Sales Channel");

    Cart actualCart = headerWebMapper.mapWebToEntity(sampleResponseBody);
    assertThat(actualCart).isEqualToComparingFieldByField(expectedCart);
  }
}
