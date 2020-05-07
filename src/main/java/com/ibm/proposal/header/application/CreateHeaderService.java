package com.ibm.proposal.header.application;

import com.ibm.proposal.header.adapter.out.persistence.RepositoryException;
import com.ibm.proposal.header.application.port.in.CreateHeaderUseCase;
import com.ibm.proposal.header.application.port.out.CreateHeaderPort;
import com.ibm.proposal.header.common.PersistenceHeaderAdapter;
import com.ibm.proposal.header.domain.Header;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.inject.Inject;

public class CreateHeaderService implements CreateHeaderUseCase {

    @Inject @PersistenceHeaderAdapter
    private CreateHeaderPort createHeaderPort;

    @Override
    public boolean createProposal(Header header) throws RepositoryException {
        return createHeaderPort.createHeader(header);
    }
}
