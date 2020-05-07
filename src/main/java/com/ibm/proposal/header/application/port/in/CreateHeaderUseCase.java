package com.ibm.proposal.header.application.port.in;

import com.ibm.proposal.header.adapter.out.persistence.RepositoryException;
import com.ibm.proposal.header.domain.Header;

public interface CreateHeaderUseCase {

    boolean createProposal(Header header) throws RepositoryException;
}
