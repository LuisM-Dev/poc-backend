package com.ibm.proposal.create.application;

import com.ibm.proposal.create.adapter.out.persistence.RepositoryException;
import com.ibm.proposal.create.application.port.in.CreateNewProposalUseCase;
import com.ibm.proposal.create.application.port.out.CreateNewProposalPort;
import com.ibm.proposal.create.common.PersistenceCreateAdapter;
import com.ibm.proposal.create.domain.Proposal;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.inject.Inject;

public class CreateNewProposalService implements CreateNewProposalUseCase {

  @Inject @PersistenceCreateAdapter
  private CreateNewProposalPort createNewProposalPort;

  @Override
  public Proposal createNewProposal(String type) throws RepositoryException {
    Proposal proposal = createNewProposalPort.createNewProposal(type);
    return proposal.makeProposal();
  }
}
