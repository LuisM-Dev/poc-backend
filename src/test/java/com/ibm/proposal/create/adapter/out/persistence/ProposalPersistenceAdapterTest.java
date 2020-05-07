package com.ibm.proposal.create.adapter.out.persistence;

import com.ibm.proposal.create.domain.Proposal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProposalPersistenceAdapterTest {

  private ProposalJpaEntity sampleProposalJpaEntity;
  private String sampleProposalType;
  @Mock
  private ProposalMapper proposalMapperMock;
  @Mock
  private ProposalRepository proposalRepositoryMock;
  @InjectMocks
  private ProposalPersistenceAdapter proposalPersistenceAdapter;

  @BeforeEach
  void setUp() {
    sampleProposalType = "Some Type";
    Proposal expectedProposal = new Proposal(sampleProposalType);
    expectedProposal.setId("1");
    expectedProposal.setType(sampleProposalType);
    sampleProposalJpaEntity = new ProposalJpaEntity();

    proposalMapperMock = mock(ProposalMapper.class);
    proposalRepositoryMock = mock(ProposalRepository.class);
    proposalPersistenceAdapter = new ProposalPersistenceAdapter();
    MockitoAnnotations.initMocks(this);

    when(proposalMapperMock.mapToJpaEntity(sampleProposalType)).thenReturn(sampleProposalJpaEntity);
    when(proposalMapperMock.mapToDomainEntity(sampleProposalJpaEntity))
            .thenReturn(expectedProposal);
  }

  @Test
  void persistProposal() throws RepositoryException {
    when(proposalRepositoryMock.create(sampleProposalJpaEntity))
        .thenReturn(sampleProposalJpaEntity);

    Proposal actualProposal = proposalPersistenceAdapter.createNewProposal(sampleProposalType);
    assertThat(actualProposal.getId()).isNotEmpty();
  }

  @Test
  void persistProposalThrowsException() {
    when(proposalRepositoryMock.create(sampleProposalJpaEntity)).thenReturn(null);
    assertThatThrownBy(
            () -> {
              proposalPersistenceAdapter.createNewProposal(sampleProposalType);
            })
        .isInstanceOf(RepositoryException.class)
        .hasMessage("failed to create new proposal");
  }
}
