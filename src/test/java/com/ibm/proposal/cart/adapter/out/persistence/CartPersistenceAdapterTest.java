package com.ibm.proposal.cart.adapter.out.persistence;

import com.ibm.proposal.cart.domain.Cart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CartPersistenceAdapterTest {

  private Cart sampleCart;
  private CartJpaEntity sampleCartJpaEntity;

  @BeforeEach
  void setUp() {
    Cart.Asset sampleCartAsset = new Cart.Asset();
    sampleCartAsset.setProductId("Some Product Id");
    sampleCartAsset.setAddLocationBasedServices("Some Location Services");
    sampleCartAsset.setInstallAt("Some Install At");
    sampleCartAsset.setInstallAtSite("Some Install At Site");
    sampleCartAsset.setInstallDate("Some Install Date");
    sampleCartAsset.setSerial("Some Serial");
    sampleCartAsset.setWarrantyEndDate("Some Warranty Date");

    List<Cart.Asset> sampleCartAssets = new ArrayList<>();
    sampleCartAssets.add(sampleCartAsset);

    sampleCart = new Cart();
    sampleCart.setProposalId("Some Proposal Id");
    sampleCart.setAssets(sampleCartAssets);

    sampleCartJpaEntity = new CartJpaEntity();
    sampleCartJpaEntity.setProductId("Some Product Id");
    sampleCartJpaEntity.setAddLocationBasedServices("Some Location Services");
    sampleCartJpaEntity.setInstallAt("Some Install At");
    sampleCartJpaEntity.setInstallAtSite("Some Install At Site");
    sampleCartJpaEntity.setInstallDate("Some Install Date");
    sampleCartJpaEntity.setSerial("Some Serial");
    sampleCartJpaEntity.setWarrantyEndDate("Some Warranty Date");
  }

  @Test
  void cartJpaEntityContainsProposalId() {
    CartJpaEntity expectedCartJpaEntity = new CartJpaEntity();
    expectedCartJpaEntity.setProposalId("Some Proposal Id");
    expectedCartJpaEntity.setProductId("Some Product Id");
    expectedCartJpaEntity.setAddLocationBasedServices("Some Location Services");
    expectedCartJpaEntity.setInstallAt("Some Install At");
    expectedCartJpaEntity.setInstallAtSite("Some Install At Site");
    expectedCartJpaEntity.setInstallDate("Some Install Date");
    expectedCartJpaEntity.setSerial("Some Serial");
    expectedCartJpaEntity.setWarrantyEndDate("Some Warranty Date");

    CartPersistenceAdapter cartPersistenceAdapter = new CartPersistenceAdapter();
    cartPersistenceAdapter.setProposalIdToAsset(sampleCart.getProposalId(), sampleCartJpaEntity);
    assertThat(sampleCartJpaEntity).isEqualToComparingFieldByField(expectedCartJpaEntity);
  }

  @Test
  void cartPersistedCorrectly() {
    sampleCartJpaEntity.setProposalId("Some Proposal Id");

    CartJpaEntity expectedCartJpaEntity = new CartJpaEntity();
    expectedCartJpaEntity.setProposalId("Some Proposal Id");
    expectedCartJpaEntity.setId(1L);

    CartRepository cartRepositoryMock = mock(CartRepository.class);
    when(cartRepositoryMock.create(sampleCartJpaEntity)).thenReturn(expectedCartJpaEntity);

    CartPersistenceAdapter cartPersistenceAdapter = new CartPersistenceAdapter();
    cartPersistenceAdapter.setCartRepository(cartRepositoryMock);

    Boolean isPersisted = cartPersistenceAdapter.createCartAsset(sampleCart);
    assertThat(isPersisted).isTrue();
  }
}
