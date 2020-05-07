package com.ibm.proposal.cart.application.port.out;

import com.ibm.proposal.cart.adapter.out.web.utils.RequestException;
import com.ibm.proposal.cart.domain.Cart;
import com.ibm.proposal.cart.domain.Services;

import java.util.List;

public interface SearchLineItemsPort {

    List<Services> searchLineItems(Cart cart) throws RequestException;
}
