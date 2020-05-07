package com.ibm.proposal.cart.application.port.out;

import com.ibm.proposal.cart.adapter.out.web.utils.RequestException;
import com.ibm.proposal.cart.domain.Cart;

public interface GetHeaderInformationPort {

    Cart getHeaderInformation(String proposalId, String proposalType) throws RequestException;
}
