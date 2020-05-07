package com.ibm.proposal.asset.application.port.out;

import com.ibm.proposal.asset.adapter.out.web.proposalleadcountry.LeadCountryException;
import com.ibm.proposal.asset.adapter.out.web.utils.RequestException;
import com.ibm.proposal.asset.domain.Asset;

public interface GetLeadCountryPort {
  String getLeadCountry(String proposalId, String proposalType) throws RequestException;
}
