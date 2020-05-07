package com.ibm.proposal.create.application;

import com.ibm.proposal.create.adapter.out.persistence.RepositoryException;
import com.ibm.proposal.create.application.port.out.CreateNewProposalPort;
import com.ibm.proposal.create.domain.Proposal;
import com.ibm.proposal.create.domain.ProposalDirect;
import com.ibm.proposal.create.domain.ProposalDistributor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CreateNewProposalServiceTest {

  @Mock
  private  CreateNewProposalPort createNewProposalPortMock;
  @InjectMocks
  private CreateNewProposalService createNewProposalService;

  @BeforeEach
  void setUp() {
    createNewProposalService = new CreateNewProposalService();
    createNewProposalPortMock = mock(CreateNewProposalPort.class);
    MockitoAnnotations.initMocks(this);
  }

  @Test
  void newDistributorProposalCreated() throws RepositoryException {
    String proposalType = "TSS Distributor Transactional";
    Proposal sampleProposal = new Proposal(proposalType);

    when(createNewProposalPortMock.createNewProposal(proposalType)).thenReturn(sampleProposal);

    Proposal proposal =
        createNewProposalService.createNewProposal(proposalType);
    assertThat(proposal).isInstanceOf(ProposalDistributor.class);
  }

  @Test
  void newDirectProposalCreated() throws RepositoryException {
    String proposalType = "TSS Direct Transactional";
    Proposal sampleProposal = new Proposal(proposalType);

    when(createNewProposalPortMock.createNewProposal(proposalType)).thenReturn(sampleProposal);

    Proposal proposal =
            createNewProposalService.createNewProposal(proposalType);
    assertThat(proposal).isInstanceOf(ProposalDirect.class);
  }
}
