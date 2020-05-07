package com.ibm.proposal.header.adapter.out.persistence;

import com.ibm.proposal.header.application.port.out.*;
import com.ibm.proposal.header.common.PersistenceHeaderAdapter;
import com.ibm.proposal.header.domain.Header;
import com.ibm.proposal.header.domain.HeaderType;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@PersistenceHeaderAdapter
public class HeaderPersistenceAdapter
    implements CreateHeaderPort, GetHeaderPort, UpdateHeaderPort {

  @Inject private HeaderRepository headerRepository;

  @Override
  public boolean createHeader(Header header) throws RepositoryException {
    HeaderJpaEntity headerJpaEntity;
    setCreatedDate(header);
    headerJpaEntity = mapHeaderToJpaEntity(header);
    HeaderJpaEntity headerJpaEntityCreated = headerRepository.create(headerJpaEntity);
    if (headerJpaEntityCreated == null) {
      throw new RepositoryException("Failed to create header");
    }
    return true;
  }

  @Override
  public Header getHeader(String proposalId, String proposalType) throws RepositoryException {
    HeaderJpaEntity foundHeaderJpaEntity = headerRepository.read(proposalId, proposalType);
    if(foundHeaderJpaEntity == null ) {
      throw new RepositoryException("Failed to find header");
    }
    return mapJpaEntityToHeader(foundHeaderJpaEntity);
  }

  @Override
  public List<Header> getHeaders() throws RepositoryException {
    List<HeaderJpaEntity> foundHeadersJpaEntity = headerRepository.readAll();
    if(foundHeadersJpaEntity.isEmpty()) {
      throw new RepositoryException("Failed to find header");
    }
    return foundHeadersJpaEntity.stream()
        .map(this::mapJpaEntityToHeader)
        .collect(Collectors.toList());
  }

  @Override
  public Header updateHeader(Header header) {
    //    HeaderDirectJpaEntity headerDirectJpaEntity = headerWebMapper.mapToJpaEntity(header);
    //    headerRepository.update(headerDirectJpaEntity);
    //    return headerWebMapper.mapToDomainEntity(headerDirectJpaEntity);
    return null;
  }

  private HeaderJpaEntity mapHeaderToJpaEntity(Header header) {
    if (isDirectProposalType(header.getType())) {
      return HeaderJpaMapper.INSTANCE.headerDirectToJpaEntity(header);
    } else {
      return HeaderJpaMapper.INSTANCE.headerDistributorToJpaEntity(header);
    }
  }

  private Header mapJpaEntityToHeader(HeaderJpaEntity headerJpaEntity) {
    if (isDirectProposalType(headerJpaEntity.getType())) {
      return HeaderJpaMapper.INSTANCE.jpaEntityToHeaderDirect(headerJpaEntity);
    } else {
      return HeaderJpaMapper.INSTANCE.jpaEntityToHeaderDistributor(headerJpaEntity);
    }
  }

  private void setCreatedDate(Header header) {
    header.setCreatedDate(LocalDate.now().toString());
  }

  private boolean isDirectProposalType(String type) {
    return type.equals(HeaderType.DIRECT.getType());
  }
}
