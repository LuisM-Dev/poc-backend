package com.ibm.proposal.asset.adapter.out.persistence;

import com.ibm.proposal.asset.domain.Asset;
import com.ibm.proposal.asset.domain.RefAsset;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AssetPersistenceAdapterTest {

  private Asset sampleAsset;
  private List<RefAsset> sampleRefAssets;
  private AssetJpaEntity sampleAssetJpaEntity;
  private AssetJpaEntity sampleAssetPersistedJpaEntity;
  @Mock private AssetMapper assetMapperMock;
  @Mock private AssetRepository assetRepositoryMock;
  @InjectMocks private AssetPersistenceAdapter assetPersistenceAdapter;

  @BeforeEach
  void setUp() {
    sampleAsset = new Asset();

    sampleRefAssets = new ArrayList<>();
    RefAsset sampleRefAsset = new RefAsset();
    sampleRefAsset.setInstallDate("Some Install Date");
    sampleRefAsset.setWarrantyEndDate("Some Warranty End Date");
    sampleRefAssets.add(sampleRefAsset);

    sampleAssetJpaEntity = new AssetJpaEntity();
    sampleAssetPersistedJpaEntity = new AssetJpaEntity();
    assetRepositoryMock = mock(AssetRepository.class);
    assetMapperMock = mock(AssetMapper.class);
    assetPersistenceAdapter = new AssetPersistenceAdapter();
    MockitoAnnotations.initMocks(this);
  }

  @Test
  void assetPersistedCorrectly() throws RepositoryException {
    sampleAssetPersistedJpaEntity.setAssetId(1L);

    when(assetMapperMock.mapToJpaEntity(sampleAsset, "Some Install Date", "Some Warranty End Date")).thenReturn(sampleAssetJpaEntity);
    when(assetRepositoryMock.create(sampleAssetJpaEntity))
        .thenReturn(sampleAssetPersistedJpaEntity);

    boolean persisted = assetPersistenceAdapter.createRefAsset(sampleAsset, sampleRefAssets);
    assertThat(persisted).isTrue();
  }

  @Test
  void assetPersistFailure() {
    sampleAssetPersistedJpaEntity.setAssetId(null);
    when(assetMapperMock.mapToJpaEntity(sampleAsset, "Some Install Date", "Some Warranty End Date")).thenReturn(sampleAssetJpaEntity);
    when(assetRepositoryMock.create(sampleAssetJpaEntity)).thenReturn(sampleAssetPersistedJpaEntity);
    assertThatThrownBy(() -> {
      assetPersistenceAdapter.createRefAsset(sampleAsset, sampleRefAssets);
    }).isInstanceOf(RepositoryException.class).hasMessage("Fail to persist assets");
  }

  @Test
  void getAssetsCorrectly() {
    String sampleId = "";
    List<AssetJpaEntity> sampleAssetJpaEntities = new ArrayList<>();
    List<RefAsset> sampleRefAssets = new ArrayList<>();
    sampleRefAssets.add(new RefAsset());

    when(assetRepositoryMock.read(sampleId)).thenReturn(new ArrayList<>());
    when(assetMapperMock.mapToDomainEntity(sampleAssetJpaEntities)).thenReturn(sampleRefAssets);

    List<RefAsset> refAssetsFound = assetPersistenceAdapter.getRefAsset(sampleId);
    assertThat(refAssetsFound).hasSize(1);
  }

  @Test
  void assetNotFound() {
    String sampleId = "Non Existent ID";
    List<AssetJpaEntity> sampleAssetJpaEntities = new ArrayList<>();
    List<RefAsset> sampleRefAssets = new ArrayList<>();

    when(assetRepositoryMock.read(sampleId)).thenReturn(new ArrayList<>());
    when(assetMapperMock.mapToDomainEntity(sampleAssetJpaEntities)).thenReturn(sampleRefAssets);

    List<RefAsset> refAssetsFound = assetPersistenceAdapter.getRefAsset(sampleId);
    assertThat(refAssetsFound).hasSize(0);
  }
}
