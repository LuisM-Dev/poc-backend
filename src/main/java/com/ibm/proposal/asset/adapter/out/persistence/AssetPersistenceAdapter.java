package com.ibm.proposal.asset.adapter.out.persistence;

import com.ibm.proposal.asset.application.port.out.CreateRefAssetPort;
import com.ibm.proposal.asset.application.port.out.GetRefAssetPort;
import com.ibm.proposal.asset.application.port.out.UpdateRefAssetPort;
import com.ibm.proposal.asset.domain.Asset;
import com.ibm.proposal.asset.domain.RefAsset;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class AssetPersistenceAdapter
    implements CreateRefAssetPort, GetRefAssetPort, UpdateRefAssetPort {

  @Inject private AssetRepository assetRepository;

  @Inject private AssetMapper assetMapper;

  @Override
  public boolean createRefAsset(Asset asset, List<RefAsset> refAssets) throws RepositoryException {
    List<Boolean> persistedStatus = new ArrayList<>();
    refAssets.forEach(refAsset -> {
      AssetJpaEntity assetJpaEntity = assetMapper.mapToJpaEntity(asset,refAsset.getInstallDate(),refAsset.getWarrantyEndDate());
      AssetJpaEntity assetJpaEntityPersisted = assetRepository.create(assetJpaEntity);
      persistedStatus.add(assetJpaEntityPersisted.getAssetId() != null);
    });
    if(!isPersistOperationSuccessful(persistedStatus)){
      throw new RepositoryException("Fail to persist assets");
    }
    return true;
  }

  @Override
  public List<RefAsset> getRefAsset(String proposalId) {
    List<AssetJpaEntity> assetsFound = assetRepository.read(proposalId);
    return assetMapper.mapToDomainEntity(assetsFound);
  }

  @Override
  public RefAsset updateRefAsset(RefAsset refAsset) {
    return null;
  }

  private boolean isPersistOperationSuccessful(List<Boolean> persistedStatus) {
    return persistedStatus.stream().filter(status -> !status).findAny().orElse(true);
  }
}
