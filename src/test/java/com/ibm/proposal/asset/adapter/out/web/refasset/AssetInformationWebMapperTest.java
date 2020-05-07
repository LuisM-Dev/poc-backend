package com.ibm.proposal.asset.adapter.out.web.refasset;

import com.ibm.proposal.asset.domain.Asset;
import com.ibm.proposal.asset.domain.RefAsset;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class AssetInformationWebMapperTest {

  private RefAssetMapper refAssetMapper;

  @BeforeEach
  void setUp() {
    refAssetMapper = new RefAssetMapper();
  }

  @Test
  void mapToRequestBodyCorrectly() {
    Asset sampleAsset = new Asset();
    sampleAsset.setProposalId("1");
    sampleAsset.setCountryCode("Some Country Code");
    sampleAsset.setAssetSerialNumber("Some Serial");
    sampleAsset.setInstalledCustomerNumber("Some Customer Number");
    sampleAsset.setModel("Some Model");
    sampleAsset.setType("Some Type");
    sampleAsset.setSoldTo("Some Sold To");
    sampleAsset.setRecordType("Some Record Type");
    String expectedRequestBody =
        "{"
            + "\"searchFilter\":"
            + "[{"
            + "\"installedCustomerNumber\":\"Some Customer Number\","
            + "\"type\":\"Some Type\","
            + "\"model\":\"Some Model\","
            + "\"assetSerialNumber\":\"Some Serial\","
            + "\"soldTo\":\"Some Sold To\","
            + "\"recordType\":\"Some Record Type\","
            + "\"countryCode\":\"Some Country Code\""
            + "}],"
            + "\"flag\":\"norm\""
            + "}";
    String requestBody = refAssetMapper.mapToRequestBody(sampleAsset);
    assertThat(requestBody).isEqualTo(expectedRequestBody);
  }

  @Test
  void mapToResponseRefAssetCorrectly() {
    String sampleResponseBody =
        "{"
            + "\"hardware\":"
            + "[{"
            + "\"installedCustomerNumber\":\"Some Customer Number\","
            + "\"type\":\"Some Type\","
            + "\"model\":\"Some Model\","
            + "\"assetSerialNumber\":\"Some Serial\","
            + "\"installDate\":\"Some Install Date\","
            + "\"warrantyEndDate\":\"Some Warranty End Date\","
            + "\"sitePartyId\":\"Some Site Party Id\""
            + "},"
            + "{"
            + "\"installedCustomerNumber\":\"Some Customer Number\","
            + "\"type\":\"Some Type\","
            + "\"model\":\"Some Model\","
            + "\"assetSerialNumber\":\"Some Serial\","
            + "\"installDate\":\"Some Install Date\","
            + "\"warrantyEndDate\":\"Some Warranty End Date\","
            + "\"sitePartyId\":\"Some Site Party Id\""
            + "}"
            + "]}";
    RefAsset expectedResponse1 = new RefAsset();
    expectedResponse1.setType("Some Type");
    expectedResponse1.setWarrantyEndDate("Some Warranty End Date");
    expectedResponse1.setSitePartyId("Some Site Party Id");
    expectedResponse1.setModel("Some Model");
    expectedResponse1.setInstalledCustomerNumber("Some Customer Number");
    expectedResponse1.setAssetSerialNumber("Some Serial");
    expectedResponse1.setInstallDate("Some Install Date");
    RefAsset expectedResponse2 = new RefAsset();
    expectedResponse2.setType("Some Type");
    expectedResponse2.setWarrantyEndDate("Some Warranty End Date");
    expectedResponse2.setSitePartyId("Some Site Party Id");
    expectedResponse2.setModel("Some Model");
    expectedResponse2.setInstalledCustomerNumber("Some Customer Number");
    expectedResponse2.setAssetSerialNumber("Some Serial");
    expectedResponse2.setInstallDate("Some Install Date");
    List<RefAsset> expectedResponses = new ArrayList<>();
    expectedResponses.add(expectedResponse1);
    expectedResponses.add(expectedResponse2);

      List<RefAsset> responses = refAssetMapper.mapToRefAsset(sampleResponseBody);
      for(int i=0; i<responses.size(); i++){
          assertThat(responses.get(i)).isEqualToComparingFieldByField(expectedResponses.get(i));
      }
  }
}
