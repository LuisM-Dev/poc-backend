package com.ibm.proposal.asset.adapter.out.persistence;

import com.ibm.proposal.asset.domain.Asset;
import com.ibm.proposal.asset.domain.RefAsset;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AssetMapper {

    AssetMapper INSTANCE = Mappers.getMapper( AssetMapper.class);

    @Mapping(source = "installDate", target = "installDate")
    @Mapping(source = "warrantyEndDate", target = "warrantyEndDate")
    AssetJpaEntity mapToJpaEntity(Asset asset, String installDate, String warrantyEndDate);

    List<RefAsset> mapToDomainEntity(List<AssetJpaEntity> assetJpaEntities);
}

