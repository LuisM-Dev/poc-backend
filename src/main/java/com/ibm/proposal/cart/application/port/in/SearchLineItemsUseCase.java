package com.ibm.proposal.cart.application.port.in;

import com.ibm.proposal.cart.adapter.out.web.utils.RequestException;
import com.ibm.proposal.cart.domain.Services;

import java.util.List;

public interface SearchLineItemsUseCase {

    List<Services> searchLineItems(String proposalId, String proposalType) throws RequestException;
}
