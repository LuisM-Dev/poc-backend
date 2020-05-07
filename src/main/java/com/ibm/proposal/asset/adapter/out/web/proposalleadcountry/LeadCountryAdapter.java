package com.ibm.proposal.asset.adapter.out.web.proposalleadcountry;

import com.ibm.proposal.asset.adapter.out.web.utils.Request;
import com.ibm.proposal.asset.adapter.out.web.utils.RequestException;
import com.ibm.proposal.asset.application.port.out.GetLeadCountryPort;
import com.ibm.proposal.asset.domain.Asset;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.inject.Inject;

public class LeadCountryAdapter implements GetLeadCountryPort {

    private static final String LEAD_COUNTRY_REQUEST_URL = "http://localhost:9080/api/proposal/header/information";

    @Inject
    private LeadCountryMapper leadCountryMapper;

    @Inject
    private Request request;

    @Override
    public String getLeadCountry(String proposalId, String proposalType) throws RequestException {
        String requestBody = leadCountryMapper.mapToRequestBody(proposalId, proposalType);
        String response = request.postRequest(LEAD_COUNTRY_REQUEST_URL,requestBody);
        return leadCountryMapper.mapToLeadCountry(response);
    }
}
