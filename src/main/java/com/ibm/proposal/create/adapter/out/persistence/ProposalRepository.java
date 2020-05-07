package com.ibm.proposal.create.adapter.out.persistence;

import lombok.Setter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class ProposalRepository {

  private static final String PERSISTENCE_UNIT_NAME = "jpapersistenceunit";
  @Setter
  private EntityManagerFactory entityManagerFactory;
  @Setter
  private EntityManager entityManager;

  ProposalJpaEntity create(ProposalJpaEntity jpaEntity) {
    initEntityManager();
    ProposalJpaEntity jpaEntityPersisted = persistInDB(jpaEntity);
    closeEntityManager();
    return jpaEntityPersisted;
  }

  ProposalJpaEntity read(Long id) {
    initEntityManager();
    ProposalJpaEntity jpaEntity = findInDB(id);
    closeEntityManager();
    return jpaEntity;
  }

  private void initEntityManager() {
    entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    entityManager = entityManagerFactory.createEntityManager();
  }

  private ProposalJpaEntity persistInDB(ProposalJpaEntity jpaEntity) {
    entityManager.getTransaction().begin();
    entityManager.persist(jpaEntity);
    entityManager.getTransaction().commit();
    return jpaEntity;
  }

  private ProposalJpaEntity findInDB(Long id) {
    return entityManager.find(ProposalJpaEntity.class, id);
  }

  private void closeEntityManager() {
    entityManagerFactory.close();
  }
}
