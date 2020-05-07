package com.ibm.proposal.header.application;

import com.ibm.proposal.header.adapter.out.persistence.RepositoryException;
import com.ibm.proposal.header.application.port.in.GetHeaderInformationUseCase;
import com.ibm.proposal.header.application.port.out.GetHeaderPort;
import com.ibm.proposal.header.common.PersistenceHeaderAdapter;
import com.ibm.proposal.header.domain.Header;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

public class GetHeaderInformationService implements GetHeaderInformationUseCase {

    @Inject @PersistenceHeaderAdapter
    private GetHeaderPort getHeaderPort;

    @Override
    public Map<String, String> getHeaderInformation(String proposalId, String proposalType) throws RepositoryException {
        Header header = getHeaderPort.getHeader(proposalId, proposalType);
        return filterHeaderInformation(header);
    }

    private Map<String, String> filterHeaderInformation(Header header) {
        Map<String, String> filteredHeaderInformation = new HashMap<>();
        filteredHeaderInformation.put("leadCountry", header.getLeadCountry());
        filteredHeaderInformation.put("startDate", header.getStartDate().toString());
        filteredHeaderInformation.put("endDate", header.getEndDate().toString());
        filteredHeaderInformation.put("pricingDate", header.getPricingDate().toString());
        filteredHeaderInformation.put("salesChannel", "J");
        return filteredHeaderInformation;
    }
}
