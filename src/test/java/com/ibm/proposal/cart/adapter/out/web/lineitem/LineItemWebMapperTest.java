package com.ibm.proposal.cart.adapter.out.web.lineitem;

import com.ibm.proposal.cart.domain.Cart;
import com.ibm.proposal.cart.domain.Services;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LineItemWebMapperTest {

  private Services.SubLineItem expectedSubLineItem;
  private List<Services.SubLineItem> expectedSubLineItems;
  private List<Services> expectedServices;
  private LineItemWebMapper lineItemWebMapper;
  private Services expectedLineItem;

  @BeforeEach
  void setUp() {
    expectedSubLineItem = new Services.SubLineItem();
    expectedSubLineItem.setStatus("Some Sub Level");
    expectedSubLineItem.setProduct("Some SubLineItem product");
    expectedSubLineItem.setType("Some SubLineItem Type");
    expectedSubLineItem.setModel("Some SubLineItem Model");
    expectedSubLineItem.setNetPrice("422.0");
    expectedSubLineItem.setSerialNumber("Some SubLineItem Serial");
    expectedSubLineItem.setStartDate("Some SubLineItem StartDate");
    expectedSubLineItem.setEndDate("Some SubLineItem EndDate");
    expectedSubLineItem.setPlatform("Some Platform");
    expectedSubLineItem.setServiceLevel("Some Service Level");
    expectedSubLineItem.setMessage("Some Sub Level: Some SubLineItem Message");

    expectedSubLineItems = new ArrayList<>();

    expectedLineItem = new Services();
    expectedLineItem.setStatus("Some Level");
    expectedLineItem.setProduct("Some Product Id");
    expectedLineItem.setPlatform("Some Platform");
    expectedLineItem.setType("Some Type");
    expectedLineItem.setModel("Some Model");
    expectedLineItem.setServiceLevel("Some Service Level");
    expectedLineItem.setSerialNumber("Some Serial");
    expectedLineItem.setStartDate("Some Start Date");
    expectedLineItem.setEndDate("Some End Date");
    expectedLineItem.setNetPrice("100.0");
    expectedLineItem.setMessage("Some Level: Some Line Message");

    expectedServices = new ArrayList<>();

    lineItemWebMapper = new LineItemWebMapper();
  }

  @Test
  void mapFromEntityToWebCorrectly() {
    Cart.Asset sampleAsset = new Cart.Asset();
    sampleAsset.setAddLocationBasedServices("Some Location Services");
    sampleAsset.setInstallAt("Some Install At");
    sampleAsset.setInstallAtSite("Some Install At Site");
    sampleAsset.setInstallDate("Some Install Date");
    sampleAsset.setProductId("Some Product Id");
    sampleAsset.setSerial("Some Serial");
    sampleAsset.setWarrantyEndDate("Some Warranty End Date");

    List<Cart.Asset> sampleAssets = new ArrayList<>();
    sampleAssets.add(sampleAsset);

    Cart sampleCart = new Cart();
    sampleCart.setProposalId("Some Proposal Id");
    sampleCart.setProposalType("Some Proposal Type");
    sampleCart.setCountry("Some Country");
    sampleCart.setPriceReferenceDate("Some Price Reference Date");
    sampleCart.setQuoteEndDate("Some Quote End Date");
    sampleCart.setQuoteStartDate("Some Quote Start Date");
    sampleCart.setSalesChannel("Some Sales Channel");
    sampleCart.setAssets(sampleAssets);

    String expectedRequestBody =
        "{"
            + "\"country\":\"Some Country\","
            + "\"quoteId\":\"Some Proposal Id\","
            + "\"quoteStartDate\":\"Some Quote Start Date\","
            + "\"quoteEndDate\":\"Some Quote End Date\","
            + "\"priceReferenceDate\":\"Some Price Reference Date\","
            + "\"salesChannel\":\"Some Sales Channel\","
            + "\"assets\":["
            + "{"
            + "\"productId\":\"Some Product Id\","
            + "\"serial\":\"Some Serial\","
            + "\"installDate\":\"Some Install Date\","
            + "\"warrantyEndDate\":\"Some Warranty End Date\","
            + "\"installAt\":\"Some Install At\","
            + "\"installAtSite\":\"Some Install At Site\","
            + "\"addLocationBasedServices\":\"Some Location Services\""
            + "}"
            + "]"
            + "}";

    LineItemWebMapper lineItemWebMapper = new LineItemWebMapper();
    String actualRequestBody = lineItemWebMapper.mapEntityToWeb(sampleCart);
    assertThat(actualRequestBody).isEqualTo(expectedRequestBody);
  }

  @Test
  void mapFromWebToEntityCorrectly() {
    String sampleResponseBody =
        "{"
            + "\"cartId\":\"1234567\","
            + "\"country\":\"AT\","
            + "\"quoteId\":\"Some Proposal Id\","
            + "\"lineItems\":["
            + "{"
            + "\"installAt\":\"677778\","
            + "\"installAtSite\":\"S027981386\","
            + "\"transactionType\":\"Add\","
            + "\"servicePid\":\"6942-73H\","
            + "\"serviceName\":\"SWMA AIX Standard Edition\","
            + "\"features\":["
            + "{"
            + "\"feature\":\"0544\","
            + "\"description\":\"charge Core Medium Tier\","
            + "\"quantity\": 1"
            + "}"
            + "],"
            + "\"mandatoryService\": false,"
            + "\"serviceStartDate\": \"Some Start Date\","
            + "\"serviceEndDate\": \"Some End Date\","
            + "\"serviceStartDateModifiable\": false,"
            + "\"serviceEndDateModifiable\": true,"
            + "\"minServiceStartDate\": \"2020-02-07\","
            + "\"maxServiceEndDate\": \"2020-02-07\","
            + "\"linkedToPrevious\": false,"
            + "\"chargeType\": \"Recurring\","
            + "\"pricingGroup\": \"Some Pricing Group\","
            + "\"annualListPrice\": \"100\","
            + "\"priceReferenceDate\": \"2020-02-07\","
            + "\"exhibitDiscount\": 26,"
            + "\"offeringGroupOrder\": \"A\","
            + "\"platform\": \"Some Platform\","
            + "\"sowPrintGroup\": \"TSA\","
            + "\"hardware\": {"
            + "\"productId\": \"Some Product Id\","
            + "\"description\": \"IBM Power System E880\","
            + "\"serial\": \"21A01D7\","
            + "\"installDate\": \"2020-02-07\","
            + "\"warrantyEndDate\": \"2020-02-07\","
            + "\"lastUpdate\": \"2015-04-30T00:00:00\""
            + "},"
            + "\"software\": {"
            + "\"productId\": \"Software Product Id\","
            + "\"description\": \"IBM Spectrum Virtualize Software for Storwize V7000 Controller Software V8\","
            + "\"serial\": \"2000\","
            + "\"lastUpdate\": \"\""
            + "},"
            + "\"subLineItems\": ["
            + "{"
            + "\"subLineItemType\": \"SWMA-S\","
            + "\"installAt\": \"677778\","
            + "\"installAtSite\": \"S027981386\","
            + "\"transactionType\": \"Add\","
            + "\"feature\": \"0544\","
            + "\"featureDescription\": \"charge Core Medium Tier\","
            + "\"quantity\": 1,"
            + "\"serviceStartDate\": \"Some SubLineItem StartDate\","
            + "\"serviceEndDate\": \"Some SubLineItem EndDate\","
            + "\"minServiceStartDate\": \"2020-02-07\","
            + "\"maxServiceEndDate\": \"2020-02-07\","
            + "\"annualListPrice\": 422,"
            + "\"unitCost\": 97.06,"
            + "\"exhibitDiscount\": 26,"
            + "\"displayOutput\": "
            + "{"
            + "\"lineName\": \"Some SubLineItem Name\","
            + "\"referenceAssetId\": \"Some SubLineItem Type-Some SubLineItem Model\","
            + "\"serial\": \"Some SubLineItem Serial\","
            + "\"referenceAssetDescription\": \"Some SubLineItem Reference Asset Description\","
            + "\"quantity\": \"1\""
            + "},"
            + "\"software\": {"
            + "\"productId\": \"Some SubLineItem product\","
            + "\"description\": \"IBM AIX Standard Edition V7\","
            + "\"serial\": \"CUBF5OF\","
            + "\"lastUpdate\": \"2015-04-30T00:00:00\""
            + "}"
            + "}"
            + "],"
            + "\"displayOutput\": {"
            + "\"lineName\": \"Some LineItem Name\","
            + "\"referenceAssetId\": \"Some Type-Some Model\","
            + "\"serial\": \"Some Serial\","
            + "\"referenceAssetDescription\": \"Some LineItem Reference Asset Description\","
            + "\"quantity\": \"1\","
            + "\"warrantyEndDate\": \"Some LineItem Warranty End Date\","
            + "\"serviceLevel\":\"Some Service Level\""
            + "},"
            + "\"messages\": ["
            + "{\n"
            + "\"messageLevel\": \"Some Sub Level\","
            + "\"messageText\": \"Some SubLineItem Message\""
            + "}"
            + "]}],"
            + "\"messages\": ["
            + "{\n"
            + "\"messageLevel\": \"Some Level\","
            + "\"messageText\": \"Some Line Message\""
            + "}"
            + "]"
            + "}";

    expectedSubLineItems.add(expectedSubLineItem);
    expectedLineItem.setSubLineItems(expectedSubLineItems);
    expectedServices.add(expectedLineItem);

    List<Services> actualServices = lineItemWebMapper.mapWebToEntity(sampleResponseBody);
    for (int i = 0; i < actualServices.size(); i++) {
      assertThat(actualServices.get(i).getStatus()).isEqualTo(expectedServices.get(i).getStatus());
      assertThat(actualServices.get(i).getProduct())
          .isEqualTo(expectedServices.get(i).getProduct());
      assertThat(actualServices.get(i).getPlatform())
          .isEqualTo(expectedServices.get(i).getPlatform());
      assertThat(actualServices.get(i).getType()).isEqualTo(expectedServices.get(i).getType());
      assertThat(actualServices.get(i).getModel()).isEqualTo(expectedServices.get(i).getModel());
      assertThat(actualServices.get(i).getServiceLevel())
          .isEqualTo(expectedServices.get(i).getServiceLevel());
      assertThat(actualServices.get(i).getSerialNumber())
          .isEqualTo(expectedServices.get(i).getSerialNumber());
      assertThat(actualServices.get(i).getStartDate())
          .isEqualTo(expectedServices.get(i).getStartDate());
      assertThat(actualServices.get(i).getEndDate())
          .isEqualTo(expectedServices.get(i).getEndDate());
      assertThat(actualServices.get(i).getNetPrice())
          .isEqualTo(expectedServices.get(i).getNetPrice());
      assertThat(actualServices.get(i).getMessage())
          .isEqualTo(expectedServices.get(i).getMessage());
      for (int j = 0; j < actualServices.get(i).getSubLineItems().size(); j++) {
        assertThat(actualServices.get(i).getSubLineItems().get(j))
            .isEqualToComparingFieldByField(expectedServices.get(i).getSubLineItems().get(j));
      }
    }
  }

  @Test
  void mapFromWebToEntityMissingAnnualPrice() {
    String sampleResponseBody =
        "{"
            + "\"cartId\":\"1234567\","
            + "\"country\":\"AT\","
            + "\"quoteId\":\"Some Proposal Id\","
            + "\"lineItems\":["
            + "{"
            + "\"installAt\":\"677778\","
            + "\"installAtSite\":\"S027981386\","
            + "\"transactionType\":\"Add\","
            + "\"servicePid\":\"6942-73H\","
            + "\"serviceName\":\"SWMA AIX Standard Edition\","
            + "\"features\":["
            + "{"
            + "\"feature\":\"0544\","
            + "\"description\":\"charge Core Medium Tier\","
            + "\"quantity\": 1"
            + "}"
            + "],"
            + "\"mandatoryService\": false,"
            + "\"serviceStartDate\": \"Some Start Date\","
            + "\"serviceEndDate\": \"Some End Date\","
            + "\"serviceStartDateModifiable\": false,"
            + "\"serviceEndDateModifiable\": true,"
            + "\"minServiceStartDate\": \"2020-02-07\","
            + "\"maxServiceEndDate\": \"2020-02-07\","
            + "\"linkedToPrevious\": false,"
            + "\"chargeType\": \"Recurring\","
            + "\"pricingGroup\": \"Some Pricing Group\","
            + "\"annualListPrice\": \"100\","
            + "\"priceReferenceDate\": \"2020-02-07\","
            + "\"exhibitDiscount\": 26,"
            + "\"offeringGroupOrder\": \"A\","
            + "\"platform\": \"Some Platform\","
            + "\"sowPrintGroup\": \"TSA\","
            + "\"hardware\": {"
            + "\"productId\": \"Some Product Id\","
            + "\"description\": \"IBM Power System E880\","
            + "\"serial\": \"21A01D7\","
            + "\"installDate\": \"2020-02-07\","
            + "\"warrantyEndDate\": \"2020-02-07\","
            + "\"lastUpdate\": \"2015-04-30T00:00:00\""
            + "},"
            + "\"subLineItems\": ["
            + "{"
            + "\"subLineItemType\": \"SWMA-S\","
            + "\"installAt\": \"677778\","
            + "\"installAtSite\": \"S027981386\","
            + "\"transactionType\": \"Add\","
            + "\"feature\": \"0544\","
            + "\"featureDescription\": \"charge Core Medium Tier\","
            + "\"quantity\": 1,"
            + "\"serviceStartDate\": \"Some SubLineItem StartDate\","
            + "\"serviceEndDate\": \"Some SubLineItem EndDate\","
            + "\"minServiceStartDate\": \"2020-02-07\","
            + "\"maxServiceEndDate\": \"2020-02-07\","
            + "\"unitCost\": 97.06,"
            + "\"exhibitDiscount\": 26,"
            + "\"displayOutput\": "
            + "{"
            + "\"lineName\": \"Some SubLineItem Name\","
            + "\"referenceAssetId\": \"Some SubLineItem Type-Some SubLineItem Model\","
            + "\"serial\": \"Some SubLineItem Serial\","
            + "\"referenceAssetDescription\": \"Some SubLineItem Reference Asset Description\","
            + "\"quantity\": \"1\""
            + "},"
            + "\"software\": {"
            + "\"productId\": \"Some SubLineItem product\","
            + "\"description\": \"IBM AIX Standard Edition V7\","
            + "\"serial\": \"CUBF5OF\","
            + "\"lastUpdate\": \"2015-04-30T00:00:00\""
            + "}"
            + "}"
            + "],"
            + "\"displayOutput\": {"
            + "\"lineName\": \"Some LineItem Name\","
            + "\"referenceAssetId\": \"Some Type-Some Model\","
            + "\"serial\": \"Some Serial\","
            + "\"referenceAssetDescription\": \"Some LineItem Reference Asset Description\","
            + "\"quantity\": \"1\","
            + "\"warrantyEndDate\": \"Some LineItem Warranty End Date\","
            + "\"serviceLevel\":\"Some Service Level\""
            + "},"
            + "\"messages\": ["
            + "{\n"
            + "\"messageLevel\": \"Some Sub Level\","
            + "\"messageText\": \"Some SubLineItem Message\""
            + "}"
            + "]}],"
            + "\"messages\": ["
            + "{\n"
            + "\"messageLevel\": \"Some Level\","
            + "\"messageText\": \"Some Line Message\""
            + "}"
            + "]"
            + "}";

    expectedSubLineItem.setNetPrice("0");
    expectedSubLineItems.add(expectedSubLineItem);
    expectedLineItem.setSubLineItems(expectedSubLineItems);
    expectedServices.add(expectedLineItem);

    List<Services> actualServices = lineItemWebMapper.mapWebToEntity(sampleResponseBody);
    for (int i = 0; i < actualServices.size(); i++) {
      assertThat(actualServices.get(i).getProduct())
          .isEqualTo(expectedServices.get(i).getProduct());
      assertThat(actualServices.get(i).getPlatform())
          .isEqualTo(expectedServices.get(i).getPlatform());
      assertThat(actualServices.get(i).getType()).isEqualTo(expectedServices.get(i).getType());
      assertThat(actualServices.get(i).getModel()).isEqualTo(expectedServices.get(i).getModel());
      assertThat(actualServices.get(i).getServiceLevel())
          .isEqualTo(expectedServices.get(i).getServiceLevel());
      assertThat(actualServices.get(i).getSerialNumber())
          .isEqualTo(expectedServices.get(i).getSerialNumber());
      assertThat(actualServices.get(i).getStartDate())
          .isEqualTo(expectedServices.get(i).getStartDate());
      assertThat(actualServices.get(i).getEndDate())
          .isEqualTo(expectedServices.get(i).getEndDate());
      assertThat(actualServices.get(i).getNetPrice())
          .isEqualTo(expectedServices.get(i).getNetPrice());
      assertThat(actualServices.get(i).getMessage())
          .isEqualTo(expectedServices.get(i).getMessage());
      for (int j = 0; j < actualServices.get(i).getSubLineItems().size(); j++) {
        assertThat(actualServices.get(i).getSubLineItems().get(j))
            .isEqualToComparingFieldByField(expectedServices.get(i).getSubLineItems().get(j));
      }
    }
  }

  @Test
  void mapFromWebToEntityEmptyQuantity() {
    String sampleResponseBody =
        "{"
            + "\"cartId\":\"1234567\","
            + "\"country\":\"AT\","
            + "\"quoteId\":\"Some Proposal Id\","
            + "\"lineItems\":["
            + "{"
            + "\"installAt\":\"677778\","
            + "\"installAtSite\":\"S027981386\","
            + "\"transactionType\":\"Add\","
            + "\"servicePid\":\"6942-73H\","
            + "\"serviceName\":\"SWMA AIX Standard Edition\","
            + "\"features\":["
            + "{"
            + "\"feature\":\"0544\","
            + "\"description\":\"charge Core Medium Tier\","
            + "\"quantity\": 1"
            + "}"
            + "],"
            + "\"mandatoryService\": false,"
            + "\"serviceStartDate\": \"Some Start Date\","
            + "\"serviceEndDate\": \"Some End Date\","
            + "\"serviceStartDateModifiable\": false,"
            + "\"serviceEndDateModifiable\": true,"
            + "\"minServiceStartDate\": \"2020-02-07\","
            + "\"maxServiceEndDate\": \"2020-02-07\","
            + "\"linkedToPrevious\": false,"
            + "\"chargeType\": \"Recurring\","
            + "\"pricingGroup\": \"Some Pricing Group\","
            + "\"annualListPrice\": \"100\","
            + "\"priceReferenceDate\": \"2020-02-07\","
            + "\"exhibitDiscount\": 26,"
            + "\"offeringGroupOrder\": \"A\","
            + "\"platform\": \"Some Platform\","
            + "\"sowPrintGroup\": \"TSA\","
            + "\"hardware\": {"
            + "\"productId\": \"Some Product Id\","
            + "\"description\": \"IBM Power System E880\","
            + "\"serial\": \"21A01D7\","
            + "\"installDate\": \"2020-02-07\","
            + "\"warrantyEndDate\": \"2020-02-07\","
            + "\"lastUpdate\": \"2015-04-30T00:00:00\""
            + "},"
            + "\"subLineItems\": ["
            + "{"
            + "\"subLineItemType\": \"SWMA-S\","
            + "\"installAt\": \"677778\","
            + "\"installAtSite\": \"S027981386\","
            + "\"transactionType\": \"Add\","
            + "\"feature\": \"0544\","
            + "\"featureDescription\": \"charge Core Medium Tier\","
            + "\"quantity\": 1,"
            + "\"serviceStartDate\": \"Some SubLineItem StartDate\","
            + "\"serviceEndDate\": \"Some SubLineItem EndDate\","
            + "\"minServiceStartDate\": \"2020-02-07\","
            + "\"maxServiceEndDate\": \"2020-02-07\","
            + "\"annualListPrice\": 422,"
            + "\"unitCost\": 97.06,"
            + "\"exhibitDiscount\": 26,"
            + "\"displayOutput\": "
            + "{"
            + "\"lineName\": \"Some SubLineItem Name\","
            + "\"referenceAssetId\": \"Some SubLineItem Type-Some SubLineItem Model\","
            + "\"serial\": \"Some SubLineItem Serial\","
            + "\"referenceAssetDescription\": \"Some SubLineItem Reference Asset Description\","
            + "\"quantity\": \"1\""
            + "},"
            + "\"software\": {"
            + "\"productId\": \"Some SubLineItem product\","
            + "\"description\": \"IBM AIX Standard Edition V7\","
            + "\"serial\": \"CUBF5OF\","
            + "\"lastUpdate\": \"2015-04-30T00:00:00\""
            + "}"
            + "}"
            + "],"
            + "\"displayOutput\": {"
            + "\"lineName\": \"Some LineItem Name\","
            + "\"referenceAssetId\": \"Some Type-Some Model\","
            + "\"serial\": \"Some Serial\","
            + "\"referenceAssetDescription\": \"Some LineItem Reference Asset Description\","
            + "\"quantity\": \"\","
            + "\"warrantyEndDate\": \"Some LineItem Warranty End Date\","
            + "\"serviceLevel\":\"Some Service Level\""
            + "},"
            + "\"messages\": ["
            + "{\n"
            + "\"messageLevel\": \"Some Sub Level\","
            + "\"messageText\": \"Some SubLineItem Message\""
            + "}"
            + "]}],"
            + "\"messages\": ["
            + "{\n"
            + "\"messageLevel\": \"Some Level\","
            + "\"messageText\": \"Some Line Message\""
            + "}"
            + "]"
            + "}";

    expectedSubLineItems.add(expectedSubLineItem);
    expectedLineItem.setSubLineItems(expectedSubLineItems);
    expectedLineItem.setNetPrice("0");
    expectedServices.add(expectedLineItem);

    List<Services> actualServices = lineItemWebMapper.mapWebToEntity(sampleResponseBody);
    for (int i = 0; i < actualServices.size(); i++) {
      assertThat(actualServices.get(i).getProduct())
          .isEqualTo(expectedServices.get(i).getProduct());
      assertThat(actualServices.get(i).getPlatform())
          .isEqualTo(expectedServices.get(i).getPlatform());
      assertThat(actualServices.get(i).getType()).isEqualTo(expectedServices.get(i).getType());
      assertThat(actualServices.get(i).getModel()).isEqualTo(expectedServices.get(i).getModel());
      assertThat(actualServices.get(i).getServiceLevel())
          .isEqualTo(expectedServices.get(i).getServiceLevel());
      assertThat(actualServices.get(i).getSerialNumber())
          .isEqualTo(expectedServices.get(i).getSerialNumber());
      assertThat(actualServices.get(i).getStartDate())
          .isEqualTo(expectedServices.get(i).getStartDate());
      assertThat(actualServices.get(i).getEndDate())
          .isEqualTo(expectedServices.get(i).getEndDate());
      assertThat(actualServices.get(i).getNetPrice())
          .isEqualTo(expectedServices.get(i).getNetPrice());
      assertThat(actualServices.get(i).getMessage())
          .isEqualTo(expectedServices.get(i).getMessage());
      for (int j = 0; j < actualServices.get(i).getSubLineItems().size(); j++) {
        assertThat(actualServices.get(i).getSubLineItems().get(j))
            .isEqualToComparingFieldByField(expectedServices.get(i).getSubLineItems().get(j));
      }
    }
  }

  @Test
  void mapFromWebToEntityNoProductId() {
    String sampleResponseBody =
        "{"
            + "\"cartId\":\"1234567\","
            + "\"country\":\"AT\","
            + "\"quoteId\":\"Some Proposal Id\","
            + "\"lineItems\":["
            + "{"
            + "\"installAt\":\"677778\","
            + "\"installAtSite\":\"S027981386\","
            + "\"transactionType\":\"Add\","
            + "\"servicePid\":\"6942-73H\","
            + "\"serviceName\":\"SWMA AIX Standard Edition\","
            + "\"features\":["
            + "{"
            + "\"feature\":\"0544\","
            + "\"description\":\"charge Core Medium Tier\","
            + "\"quantity\": 1"
            + "}"
            + "],"
            + "\"mandatoryService\": false,"
            + "\"serviceStartDate\": \"Some Start Date\","
            + "\"serviceEndDate\": \"Some End Date\","
            + "\"serviceStartDateModifiable\": false,"
            + "\"serviceEndDateModifiable\": true,"
            + "\"minServiceStartDate\": \"2020-02-07\","
            + "\"maxServiceEndDate\": \"2020-02-07\","
            + "\"linkedToPrevious\": false,"
            + "\"chargeType\": \"Recurring\","
            + "\"pricingGroup\": \"Some Pricing Group\","
            + "\"annualListPrice\": \"100\","
            + "\"priceReferenceDate\": \"2020-02-07\","
            + "\"exhibitDiscount\": 26,"
            + "\"offeringGroupOrder\": \"A\","
            + "\"platform\": \"Some Platform\","
            + "\"sowPrintGroup\": \"TSA\","
            + "\"hardware\": {"
            + "\"productId\": \"Some Product Id\","
            + "\"description\": \"IBM Power System E880\","
            + "\"serial\": \"21A01D7\","
            + "\"installDate\": \"2020-02-07\","
            + "\"warrantyEndDate\": \"2020-02-07\","
            + "\"lastUpdate\": \"2015-04-30T00:00:00\""
            + "},"
            + "\"subLineItems\": ["
            + "{"
            + "\"subLineItemType\": \"SWMA-S\","
            + "\"installAt\": \"677778\","
            + "\"installAtSite\": \"S027981386\","
            + "\"transactionType\": \"Add\","
            + "\"feature\": \"0544\","
            + "\"featureDescription\": \"charge Core Medium Tier\","
            + "\"quantity\": 1,"
            + "\"serviceStartDate\": \"Some SubLineItem StartDate\","
            + "\"serviceEndDate\": \"Some SubLineItem EndDate\","
            + "\"minServiceStartDate\": \"2020-02-07\","
            + "\"maxServiceEndDate\": \"2020-02-07\","
            + "\"annualListPrice\": 422,"
            + "\"unitCost\": 97.06,"
            + "\"exhibitDiscount\": 26,"
            + "\"displayOutput\": "
            + "{"
            + "\"lineName\": \"Some SubLineItem Name\","
            + "\"referenceAssetId\": \"Some SubLineItem Type-Some SubLineItem Model\","
            + "\"serial\": \"Some SubLineItem Serial\","
            + "\"referenceAssetDescription\": \"Some SubLineItem Reference Asset Description\","
            + "\"quantity\": \"1\""
            + "}"
            + "}"
            + "],"
            + "\"displayOutput\": {"
            + "\"lineName\": \"Some LineItem Name\","
            + "\"referenceAssetId\": \"Some Type-Some Model\","
            + "\"serial\": \"Some Serial\","
            + "\"referenceAssetDescription\": \"Some LineItem Reference Asset Description\","
            + "\"quantity\": \"1\","
            + "\"warrantyEndDate\": \"Some LineItem Warranty End Date\","
            + "\"serviceLevel\":\"Some Service Level\""
            + "},"
            + "\"messages\": ["
            + "{\n"
            + "\"messageLevel\": \"Some Sub Level\","
            + "\"messageText\": \"Some SubLineItem Message\""
            + "}"
            + "]}],"
            + "\"messages\": ["
            + "{\n"
            + "\"messageLevel\": \"Some Level\","
            + "\"messageText\": \"Some Line Message\""
            + "}"
            + "]"
            + "}";

    expectedSubLineItem.setProduct("");
    expectedSubLineItems.add(expectedSubLineItem);
    expectedLineItem.setSubLineItems(expectedSubLineItems);
    expectedServices.add(expectedLineItem);

    List<Services> actualServices = lineItemWebMapper.mapWebToEntity(sampleResponseBody);
    for (int i = 0; i < actualServices.size(); i++) {
      assertThat(actualServices.get(i).getProduct())
          .isEqualTo(expectedServices.get(i).getProduct());
      assertThat(actualServices.get(i).getPlatform())
          .isEqualTo(expectedServices.get(i).getPlatform());
      assertThat(actualServices.get(i).getType()).isEqualTo(expectedServices.get(i).getType());
      assertThat(actualServices.get(i).getModel()).isEqualTo(expectedServices.get(i).getModel());
      assertThat(actualServices.get(i).getServiceLevel())
          .isEqualTo(expectedServices.get(i).getServiceLevel());
      assertThat(actualServices.get(i).getSerialNumber())
          .isEqualTo(expectedServices.get(i).getSerialNumber());
      assertThat(actualServices.get(i).getStartDate())
          .isEqualTo(expectedServices.get(i).getStartDate());
      assertThat(actualServices.get(i).getEndDate())
          .isEqualTo(expectedServices.get(i).getEndDate());
      assertThat(actualServices.get(i).getNetPrice())
          .isEqualTo(expectedServices.get(i).getNetPrice());
      assertThat(actualServices.get(i).getMessage())
          .isEqualTo(expectedServices.get(i).getMessage());
      for (int j = 0; j < actualServices.get(i).getSubLineItems().size(); j++) {
        assertThat(actualServices.get(i).getSubLineItems().get(j))
            .isEqualToComparingFieldByField(expectedServices.get(i).getSubLineItems().get(j));
      }
    }
  }
}
