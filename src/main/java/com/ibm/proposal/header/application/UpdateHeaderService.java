package com.ibm.proposal.header.application;

import com.ibm.proposal.header.application.port.in.UpdateHeaderUseCase;
import com.ibm.proposal.header.application.port.out.UpdateHeaderPort;
import com.ibm.proposal.header.common.PersistenceHeaderAdapter;
import com.ibm.proposal.header.domain.Header;

import javax.inject.Inject;

public class UpdateHeaderService implements UpdateHeaderUseCase {

    @Inject @PersistenceHeaderAdapter
    UpdateHeaderPort updateHeaderPort;

    @Override
    public Header updateProposal(Header proposalHeader) {
        return updateHeaderPort.updateHeader(proposalHeader);
    }
}
