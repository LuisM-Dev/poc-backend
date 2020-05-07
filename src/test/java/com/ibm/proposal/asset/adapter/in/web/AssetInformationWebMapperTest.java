package com.ibm.proposal.asset.adapter.in.web;

import com.ibm.proposal.asset.domain.Asset;
import com.ibm.proposal.asset.domain.RefAsset;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class AssetInformationWebMapperTest {

  @Test
  void mapToDomainCorrectly() {
    AssetWebModel sampleAssetWeb = new AssetWebModel();
    sampleAssetWeb.setProposalId("1");
    sampleAssetWeb.setProposalType("Some Proposal Type");
    sampleAssetWeb.setType("Some Type");
    sampleAssetWeb.setModel("Some Model");
    sampleAssetWeb.setInstalledCustomerNumber("Some Customer Number");
    sampleAssetWeb.setAssetSerialNumber("Some Serial Number");

    Asset expectedAsset = new Asset();
    expectedAsset.setProposalId("1");
    expectedAsset.setProposalType("Some Proposal Type");
    expectedAsset.setRecordType("ibmcTSSDirectTransactionalQuote");
    expectedAsset.setType("Some Type");
    expectedAsset.setModel("Some Model");
    expectedAsset.setInstalledCustomerNumber("Some Customer Number");
    expectedAsset.setAssetSerialNumber("Some Serial Number");

    Asset actualAsset = AssetWebMapper.INSTANCE.mapToEntityDomain(sampleAssetWeb);
    assertThat(actualAsset).isEqualToComparingFieldByField(expectedAsset);
  }

  @Test
  void mapToWebRefAssetCorrectly() {
    RefAsset sampleRefAsset = new RefAsset();
    sampleRefAsset.setType("Some Type");
    sampleRefAsset.setWarrantyEndDate("Some Warranty End Date");
    sampleRefAsset.setSitePartyId("Some Site Party");
    sampleRefAsset.setModel("Some Model");
    sampleRefAsset.setInstalledCustomerNumber("Some Customer Number");
    sampleRefAsset.setInstallDate("Some Install Date");
    sampleRefAsset.setAssetSerialNumber("Some Asset Serial Number");

    List<RefAsset> sampleRefAssets = new ArrayList<>();
    sampleRefAssets.add(sampleRefAsset);

    RefAssetWebModel expectedRefAssetWebModel = new RefAssetWebModel();
    expectedRefAssetWebModel.setType("Some Type");
    expectedRefAssetWebModel.setWarrantyEndDate("Some Warranty End Date");
    expectedRefAssetWebModel.setSitePartyId("Some Site Party");
    expectedRefAssetWebModel.setModel("Some Model");
    expectedRefAssetWebModel.setInstalledCustomerNumber("Some Customer Number");
    expectedRefAssetWebModel.setInstallDate("Some Install Date");
    expectedRefAssetWebModel.setAssetSerialNumber("Some Asset Serial Number");

    List<RefAssetWebModel> expectedRefAssetsWebModel = new ArrayList<>();
    expectedRefAssetsWebModel.add(expectedRefAssetWebModel);

    List<RefAssetWebModel> actualRefAssetsWebModel = AssetWebMapper.INSTANCE.mapToWebDomain(sampleRefAssets);
    assertThat(actualRefAssetsWebModel.iterator().next()).isEqualToComparingFieldByField(expectedRefAssetsWebModel.iterator().next());
  }
}
