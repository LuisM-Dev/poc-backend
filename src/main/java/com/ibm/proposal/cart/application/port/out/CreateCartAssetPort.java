package com.ibm.proposal.cart.application.port.out;

import com.ibm.proposal.cart.domain.Cart;

public interface CreateCartAssetPort {

    Boolean createCartAsset(Cart cart);
}
