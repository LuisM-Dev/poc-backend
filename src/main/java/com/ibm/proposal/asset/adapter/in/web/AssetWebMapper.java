package com.ibm.proposal.asset.adapter.in.web;

import com.ibm.proposal.asset.domain.Asset;
import com.ibm.proposal.asset.domain.RefAsset;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AssetWebMapper {
  AssetWebMapper INSTANCE = Mappers.getMapper(AssetWebMapper.class);

  @Mapping(source="proposalType", target = "recordType", qualifiedByName = "mapRecordType")
  Asset mapToEntityDomain(AssetWebModel assetWebModel);

  @Named("mapRecordType")
  static String mapRecordType(String proposalType) {
    return proposalType.equals("TSS Distributor Transactional") ? "ibmcTSSDistributorTransactionalQuote" : "ibmcTSSDirectTransactionalQuote";
  }

  List<RefAssetWebModel> mapToWebDomain(List<RefAsset> refAsset);
}
