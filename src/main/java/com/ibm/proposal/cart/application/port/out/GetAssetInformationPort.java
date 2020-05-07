package com.ibm.proposal.cart.application.port.out;

import com.ibm.proposal.cart.adapter.out.web.utils.RequestException;
import com.ibm.proposal.cart.domain.Cart;

import java.util.List;

public interface GetAssetInformationPort {

    List<Cart.Asset> getAssetInformation(String proposalId, String proposalType) throws RequestException;
}
