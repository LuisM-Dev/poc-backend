package com.ibm.proposal.cart.adapter.out.web.headerinformation;

import com.ibm.proposal.cart.adapter.out.web.utils.Request;
import com.ibm.proposal.cart.adapter.out.web.utils.RequestException;
import com.ibm.proposal.cart.application.port.out.GetHeaderInformationPort;
import com.ibm.proposal.cart.domain.Cart;

import javax.inject.Inject;

public class HeaderWebAdapter implements GetHeaderInformationPort {

  private static final String HEADER_INFO_REQUEST_URL =
      "http://localhost:9080/api/proposal/header/information";

  @Inject HeaderWebMapper headerWebMapper;

  @Inject Request request;

  @Override
  public Cart getHeaderInformation(String proposalId, String proposalType) throws RequestException {
    String requestBody = headerWebMapper.mapEntityToWeb(proposalId, proposalType);
    String responseBody = request.postRequest(HEADER_INFO_REQUEST_URL, requestBody);
    Cart cartWithHeaderInfo = headerWebMapper.mapWebToEntity(responseBody);
    cartWithHeaderInfo.setProposalId(proposalId);
    cartWithHeaderInfo.setProposalType(proposalType);
    return cartWithHeaderInfo;
  }
}
