package com.ibm.proposal.create.adapter.out.persistence;

import com.ibm.proposal.create.domain.Proposal;
import com.ibm.proposal.header.adapter.out.persistence.HeaderDirectJpaEntity;
import com.ibm.proposal.header.adapter.out.persistence.HeaderDistributorJpaEntity;
import com.ibm.proposal.header.domain.HeaderDistributor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class ProposalMapperTest {

  private ProposalMapper proposalMapper;

  @BeforeEach
  void setUp() {
    proposalMapper = new ProposalMapper();
  }

  @Test
  void shouldMapToJpaEntity() {
    String proposalType = "Some Type";
    ProposalJpaEntity proposalJpaEntity = proposalMapper.mapToJpaEntity(proposalType);
    assertThat(proposalJpaEntity).isInstanceOf(ProposalJpaEntity.class);
    assertThat(proposalJpaEntity.getType()).isNotEmpty();
  }

  @Test
  void shouldMapToDomainEntityWithId() {
    ProposalJpaEntity proposalJpaEntity = new ProposalJpaEntity();
    proposalJpaEntity.setType("Some Type");
    proposalJpaEntity.setId(1L);
    Proposal proposal = proposalMapper.mapToDomainEntity(proposalJpaEntity);
    assertThat(proposal).isInstanceOf(Proposal.class);
    assertThat(proposal.getId()).isNotEmpty();
  }
}
