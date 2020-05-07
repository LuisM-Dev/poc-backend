package com.ibm.proposal.create.application.port.in;

import com.ibm.proposal.create.adapter.out.persistence.RepositoryException;
import com.ibm.proposal.create.domain.Proposal;

public interface CreateNewProposalUseCase {

    Proposal createNewProposal(String type) throws RepositoryException;
}
