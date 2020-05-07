package com.ibm.proposal.create.adapter.out.persistence;

import com.ibm.proposal.create.domain.Proposal;

public class ProposalMapper {

    ProposalJpaEntity mapToJpaEntity(String type) {
        ProposalJpaEntity jpaEntity = new ProposalJpaEntity();
        jpaEntity.setType(type);
        return jpaEntity;
    }

    Proposal mapToDomainEntity(ProposalJpaEntity jpaEntity) {
        return new Proposal(jpaEntity.getId().toString(),jpaEntity.getType());
    }
}
