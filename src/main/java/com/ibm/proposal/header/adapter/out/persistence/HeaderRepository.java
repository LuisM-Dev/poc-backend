package com.ibm.proposal.header.adapter.out.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class HeaderRepository {

  private static final String PERSISTENCE_UNIT_NAME = "jpapersistenceunit";
  private EntityManagerFactory entityManagerFactory;

  public HeaderRepository() {
    entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
  }

  public HeaderJpaEntity create(HeaderJpaEntity headerJpaEntity) {
    try {
      return saveInDB(headerJpaEntity);
    } catch (Exception e) {
      return null;
    }
  }

  public HeaderJpaEntity read(String id, String type) {
    try {
      return findInDB(id, type);
    } catch (Exception e) {
      return null;
    }
  }

  public List<HeaderJpaEntity> readAll() {
    try {
      return findAll();
    } catch (Exception e) {
      return new ArrayList<>();
    }
  }

  public HeaderJpaEntity update(HeaderJpaEntity headerJpaEntity) {
    try {
      return updateInDB(headerJpaEntity);
    } catch (Exception e) {
      return null;
    }
  }

  private HeaderJpaEntity saveInDB(HeaderJpaEntity headerJpaEntity){
    EntityManager entityManager = beginTransaction();
    entityManager.persist(headerJpaEntity);
    commitTransaction(entityManager);
    return headerJpaEntity;
  }

  private HeaderJpaEntity updateInDB(HeaderJpaEntity headerJpaEntity) {
    EntityManager entityManager = beginTransaction();
    entityManager.merge(headerJpaEntity);
    commitTransaction(entityManager);
    return headerJpaEntity;
  }

  private HeaderJpaEntity findInDB(String id, String type) {
    HeaderJpaEntity headerJpaEntity;
    if(type.equals("TSS Distributor Transactional")){
      headerJpaEntity = initEntityManager().find(HeaderDistributorJpaEntity.class, id);
    } else {
      headerJpaEntity = initEntityManager().find(HeaderDirectJpaEntity.class, id);
    }
    closeEntityManager();
    return headerJpaEntity;
  }

  private List<HeaderJpaEntity> findAll() {
    List<HeaderJpaEntity> headerJpaEntities = new ArrayList<>();
    EntityManager entityManager = initEntityManager();
    List<HeaderDirectJpaEntity> headerDirectJpaEntities =
            entityManager
                    .createQuery(
                            "Select h from HeaderDirectJpaEntity h",
                            HeaderDirectJpaEntity.class)
                    .getResultList();
    List<HeaderDistributorJpaEntity> headerDistributorJpaEntities =
            entityManager
                    .createQuery(
                            "Select h from HeaderDistributorJpaEntity h",
                            HeaderDistributorJpaEntity.class)
                    .getResultList();
    closeEntityManager();
    headerJpaEntities.addAll(headerDirectJpaEntities);
    headerJpaEntities.addAll(headerDistributorJpaEntities);
    return headerJpaEntities;
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
    entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    return entityManagerFactory.createEntityManager();
  }

  private void closeEntityManager() {
    entityManagerFactory.close();
  }
}
