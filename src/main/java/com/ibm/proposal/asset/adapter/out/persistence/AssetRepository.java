package com.ibm.proposal.asset.adapter.out.persistence;

import com.ibm.proposal.asset.domain.Asset;

import javax.persistence.*;
import java.util.List;

public class AssetRepository {

  private static final String PERSISTENCE_UNIT_NAME = "jpapersistenceunit";
  private final EntityManagerFactory entityManagerFactory;

  public AssetRepository() {
    entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
  }

  public AssetJpaEntity create(AssetJpaEntity assetJpaEntity) {
    return saveInDB(assetJpaEntity);
  }

  public List<AssetJpaEntity> read(String proposalId) {
    return findByProposalId(proposalId);
  }

  public AssetJpaEntity update(AssetJpaEntity assetJpaEntity) {
    return updateInDB(assetJpaEntity);
  }

  private AssetJpaEntity saveInDB(AssetJpaEntity assetJpaEntity) {
    EntityManager entityManager = beginTransaction();
    entityManager.persist(assetJpaEntity);
    commitTransaction(entityManager);
    return assetJpaEntity;
  }

  private AssetJpaEntity updateInDB(AssetJpaEntity assetJpaEntity) {
    EntityManager entityManager = beginTransaction();
    entityManager.merge(assetJpaEntity);
    commitTransaction(entityManager);
    return assetJpaEntity;
  }

  private AssetJpaEntity findById(Long id) {
    EntityManager entityManager = initEntityManager();
    AssetJpaEntity assetJpaEntity = entityManager.find(AssetJpaEntity.class, id);
    closeEntityManager();
    return assetJpaEntity;
  }

  private List<AssetJpaEntity> findByProposalId(String proposalId) {
    EntityManager entityManager = initEntityManager();
    List<AssetJpaEntity> assetJpaEntities =
        entityManager
            .createQuery(
                "Select a from AssetJpaEntity a where a.proposalId=:proposalId",
                AssetJpaEntity.class)
            .setParameter("proposalId", proposalId)
            .getResultList();
    closeEntityManager();
    return assetJpaEntities;
  }

  private EntityManager beginTransaction() {
    EntityManager entityManager = initEntityManager();
    entityManager.getTransaction().begin();
    return entityManager;
  }

  private void commitTransaction(EntityManager entityManager) {
    entityManager.getTransaction().commit();
    closeEntityManager();
  }

  private EntityManager initEntityManager() {
    return entityManagerFactory.createEntityManager();
  }

  private void closeEntityManager() {
    entityManagerFactory.close();
  }
}
