package com.ibm.proposal.cart.adapter.out.persistence;

import com.ibm.proposal.cart.application.port.out.CreateCartAssetPort;
import com.ibm.proposal.cart.domain.Cart;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class CartPersistenceAdapter implements CreateCartAssetPort {

    @Inject
    private CartRepository cartRepository;

    @Override
    public Boolean createCartAsset(Cart cart) {
        List<Boolean> persistedAssetStatus = new ArrayList<>();
        persistCartAssets(cart, persistedAssetStatus);
        return isPersistOperationSuccessfully(persistedAssetStatus);
    }

    private void persistCartAssets(Cart cart, List<Boolean> persistedAssetStatus) {
        cart.getAssets().forEach(asset -> {
            CartJpaEntity cartJpaEntity = createCartAssetJpaEntity(cart, asset);
            CartJpaEntity persistedCartJpaEntity = cartRepository.create(cartJpaEntity);
            persistedAssetStatus.add(persistedCartJpaEntity.getId() != null);
        });
    }

    private CartJpaEntity createCartAssetJpaEntity(Cart cart, Cart.Asset asset) {
        CartJpaEntity cartJpaEntity = CartPersistenceMapper.INSTANCE.mapEntityToJpa(asset);
        setProposalIdToAsset(cart.getProposalId(),cartJpaEntity);
        return cartJpaEntity;
    }

    void setProposalIdToAsset(String proposalId, CartJpaEntity cartJpaEntity) {
        cartJpaEntity.setProposalId(proposalId);
    }

    private Boolean isPersistOperationSuccessfully(List<Boolean> persistedAssetStatus) {
        return persistedAssetStatus.stream().filter(status -> !status).findAny().orElse(true);
    }
}
