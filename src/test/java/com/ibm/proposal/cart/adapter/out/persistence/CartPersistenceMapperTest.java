package com.ibm.proposal.cart.adapter.out.persistence;

import com.ibm.proposal.cart.domain.Cart;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CartPersistenceMapperTest {
    @Test
    void mapEntityToJpaCorrectly() {
        Cart.Asset sampleCartAsset = new Cart.Asset();
        sampleCartAsset.setProductId("Some Product Id");
        sampleCartAsset.setAddLocationBasedServices("Some Location Services");
        sampleCartAsset.setInstallAt("Some Install At");
        sampleCartAsset.setInstallAtSite("Some Install At Site");
        sampleCartAsset.setInstallDate("Some Install Date");
        sampleCartAsset.setSerial("Some Serial");
        sampleCartAsset.setWarrantyEndDate("Some Warranty Date");

        CartJpaEntity expectedCartJpaEntity = new CartJpaEntity();
        expectedCartJpaEntity.setProductId("Some Product Id");
        expectedCartJpaEntity.setAddLocationBasedServices("Some Location Services");
        expectedCartJpaEntity.setInstallAt("Some Install At");
        expectedCartJpaEntity.setInstallAtSite("Some Install At Site");
        expectedCartJpaEntity.setInstallDate("Some Install Date");
        expectedCartJpaEntity.setSerial("Some Serial");
        expectedCartJpaEntity.setWarrantyEndDate("Some Warranty Date");

        CartJpaEntity actualCartJpaEntity = CartPersistenceMapper.INSTANCE.mapEntityToJpa(sampleCartAsset);
        assertThat(actualCartJpaEntity).isEqualTo(expectedCartJpaEntity);
        assertThat(actualCartJpaEntity.getId()).isNull();
        assertThat(actualCartJpaEntity.getProposalId()).isNull();
    }
}
