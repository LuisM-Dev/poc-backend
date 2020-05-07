package com.ibm.proposal.create.application.port.out;

import com.ibm.proposal.create.adapter.out.persistence.RepositoryException;
import com.ibm.proposal.create.domain.Proposal;

public interface CreateNewProposalPort {

    Proposal createNewProposal(String type) throws RepositoryException;
}
