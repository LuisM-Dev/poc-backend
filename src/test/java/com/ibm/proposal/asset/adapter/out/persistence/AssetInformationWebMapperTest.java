package com.ibm.proposal.asset.adapter.out.persistence;

import com.ibm.proposal.asset.adapter.out.persistence.AssetJpaEntity;
import com.ibm.proposal.asset.adapter.out.persistence.AssetMapper;
import com.ibm.proposal.asset.domain.Asset;
import com.ibm.proposal.asset.domain.RefAsset;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class AssetInformationWebMapperTest {

  @Test
  void mapToJpaEntityCorrectly() {
    Asset sampleAsset = new Asset();
    sampleAsset.setProposalId("1");
    sampleAsset.setRecordType("Some Record Type");
    sampleAsset.setSoldTo("Some Sold To");
    sampleAsset.setType("Some Type");
    sampleAsset.setModel("Some Model");
    sampleAsset.setInstalledCustomerNumber("Some Customer Number");
    sampleAsset.setCountryCode("Some Country Code");
    sampleAsset.setAssetSerialNumber("Some Serial Number");
    String sampleInstallDate = "Some Install Date";
    String sampleWarrantyEndDate = "Some Warranty End Date";

    AssetJpaEntity expectedAssetJpaEntity = new AssetJpaEntity();
    expectedAssetJpaEntity.setAssetSerialNumber("Some Serial Number");
    expectedAssetJpaEntity.setCountryCode("Some Country Code");
    expectedAssetJpaEntity.setInstalledCustomerNumber("Some Customer Number");
    expectedAssetJpaEntity.setModel("Some Model");
    expectedAssetJpaEntity.setType("Some Type");
    expectedAssetJpaEntity.setRecordType("Some Record Type");
    expectedAssetJpaEntity.setSoldTo("Some Sold To");
    expectedAssetJpaEntity.setProposalId("1");
    expectedAssetJpaEntity.setInstallDate("Some Install Date");
    expectedAssetJpaEntity.setWarrantyEndDate("Some Warranty End Date");

    AssetJpaEntity actualAssetJpaEntity =
        AssetMapper.INSTANCE.mapToJpaEntity(sampleAsset, sampleInstallDate, sampleWarrantyEndDate);
    assertThat(actualAssetJpaEntity).isEqualToComparingFieldByField(expectedAssetJpaEntity);
  }

  @Test
  void mapToDomainEntity() {
    AssetJpaEntity sampleAssetJpaEntity = new AssetJpaEntity();
    sampleAssetJpaEntity.setAssetSerialNumber("Some Serial Number");
    sampleAssetJpaEntity.setCountryCode("Some Country Code");
    sampleAssetJpaEntity.setInstalledCustomerNumber("Some Customer Number");
    sampleAssetJpaEntity.setModel("Some Model");
    sampleAssetJpaEntity.setType("Some Type");
    sampleAssetJpaEntity.setRecordType("Some Record Type");
    sampleAssetJpaEntity.setSoldTo("Some Sold To");
    sampleAssetJpaEntity.setProposalId("1");
    sampleAssetJpaEntity.setAssetId(1L);
    sampleAssetJpaEntity.setInstallDate("Some Install Date");
    sampleAssetJpaEntity.setWarrantyEndDate("Some Warranty End Date");
    List<AssetJpaEntity> sampleAssetJpaEntities = new ArrayList<>();
    sampleAssetJpaEntities.add(sampleAssetJpaEntity);

    RefAsset expectedRefAsset = new RefAsset();
    expectedRefAsset.setType("Some Type");
    expectedRefAsset.setModel("Some Model");
    expectedRefAsset.setInstalledCustomerNumber("Some Customer Number");
    expectedRefAsset.setAssetSerialNumber("Some Serial Number");
    expectedRefAsset.setInstallDate("Some Install Date");
    expectedRefAsset.setWarrantyEndDate("Some Warranty End Date");
    List<RefAsset> expectedRefAssets = new ArrayList<>();
    expectedRefAssets.add(expectedRefAsset);

    List<RefAsset> actualRefAssets = AssetMapper.INSTANCE.mapToDomainEntity(sampleAssetJpaEntities);
    assertThat(actualRefAssets.iterator().next()).isEqualToComparingFieldByField(expectedRefAssets.iterator().next());
  }
}
