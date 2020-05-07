package com.ibm.proposal.create.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ProposalTest {

  private Proposal proposal;

  @BeforeEach
  void setUp() {
    proposal = new Proposal("TSS Distributor Transactional");
  }

  @Test
  void getDirectProposal() {
    proposal.setType("TSS Direct Transactional");
    Proposal proposalDirect = proposal.makeProposal();
    assertThat(proposalDirect).isInstanceOf(ProposalDirect.class);
  }

  @Test
  void getDistributorProposal() {
    Proposal proposalDistributor = proposal.makeProposal();
    assertThat(proposalDistributor).isInstanceOf(ProposalDistributor.class);
  }

  @Test
  void getDistributorProposalWithDefaultFields() {
    Proposal proposalDistributor = proposal.makeProposal();
    assertThat(proposalDistributor.getFields().size()).isGreaterThan(0);
  }

  @Test
  void getDirectProposalWithDefaultFields() {
    proposal.setType("TSS Direct Transactional");
    Proposal proposalDistributor = proposal.makeProposal();
    assertThat(proposalDistributor.getFields().size()).isGreaterThan(0);
  }
}
