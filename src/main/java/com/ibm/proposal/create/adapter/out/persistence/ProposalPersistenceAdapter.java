package com.ibm.proposal.create.adapter.out.persistence;

import com.ibm.proposal.create.application.port.out.CreateNewProposalPort;
import com.ibm.proposal.create.common.PersistenceCreateAdapter;
import com.ibm.proposal.create.domain.Proposal;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.inject.Inject;

@PersistenceCreateAdapter
public class ProposalPersistenceAdapter implements CreateNewProposalPort {

    @Inject
    private ProposalRepository proposalRepository;

    @Inject
    private ProposalMapper proposalMapper;

    @Override
    public Proposal createNewProposal(String type) throws RepositoryException {
        ProposalJpaEntity proposalJpaEntity = proposalMapper.mapToJpaEntity(type);
        ProposalJpaEntity proposalJpaEntityPersisted = proposalRepository.create(proposalJpaEntity);
        if(proposalJpaEntityPersisted == null) {
            throw new RepositoryException("failed to create new proposal");
        }
        return proposalMapper.mapToDomainEntity(proposalJpaEntityPersisted);
    }
}
