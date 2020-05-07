package com.ibm.proposal.cart.adapter.out.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CartRepository {

    private static final String PERSISTENCE_UNIT_NAME = "jpapersistenceunit";
    private final EntityManagerFactory entityManagerFactory;

    public CartRepository() {
        entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }

    public CartJpaEntity create(CartJpaEntity cartJpaEntity) {
        return saveInDB(cartJpaEntity);
    }

    public CartJpaEntity read(Long id) {
        return findInDB(id);
    }

    public CartJpaEntity update(CartJpaEntity cartJpaEntity) {
        return updateInDB(cartJpaEntity);
    }

    private CartJpaEntity saveInDB(CartJpaEntity cartJpaEntity) {
        EntityManager entityManager = beginTransaction();
        entityManager.persist(cartJpaEntity);
        commitTransaction(entityManager);
        return cartJpaEntity;
    }

    private CartJpaEntity updateInDB(CartJpaEntity cartJpaEntity) {
        EntityManager entityManager = beginTransaction();
        entityManager.merge(cartJpaEntity);
        commitTransaction(entityManager);
        return cartJpaEntity;
    }

    private CartJpaEntity findInDB(Long id) {
        EntityManager entityManager = initEntityManager();
        CartJpaEntity cartJpaEntity = entityManager.find(CartJpaEntity.class, id);
        closeEntityManager();
        return cartJpaEntity;
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
