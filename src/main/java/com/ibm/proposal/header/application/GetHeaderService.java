package com.ibm.proposal.header.application;

import com.ibm.proposal.header.adapter.out.persistence.RepositoryException;
import com.ibm.proposal.header.application.port.in.GetHeaderUseCase;
import com.ibm.proposal.header.application.port.out.GetHeaderPort;
import com.ibm.proposal.header.common.PersistenceHeaderAdapter;
import com.ibm.proposal.header.domain.Header;

import javax.inject.Inject;
import java.util.List;

public class GetHeaderService implements GetHeaderUseCase {

    @Inject @PersistenceHeaderAdapter
    GetHeaderPort getHeaderPort;

    @Override
    public Header getHeader(String proposalId, String proposalType) throws RepositoryException {
        return getHeaderPort.getHeader(proposalId, proposalType);
    }

    @Override
    public List<Header> getHeaders() throws RepositoryException {
        return getHeaderPort.getHeaders();
    }
}
