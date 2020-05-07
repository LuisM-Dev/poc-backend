package com.ibm.proposal.header.adapter.out;

import com.ibm.proposal.header.adapter.out.persistence.*;
import com.ibm.proposal.header.domain.Header;
import com.ibm.proposal.header.domain.HeaderDirect;
import com.ibm.proposal.header.domain.HeaderDistributor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Matchers.booleanThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class HeaderPersistenceAdapterTest {

  private Header sampleHeaderDirect;
  private Header sampleHeaderDistributor;
  private HeaderDirectJpaEntity sampleHeaderDirectJpaEntity;
  private HeaderDistributorJpaEntity sampleHeaderDistributorJpaEntity;
  @Mock
  private HeaderRepository headerRepositoryMock;
  @InjectMocks
  private HeaderPersistenceAdapter headerPersistenceAdapter;
  private String sampleId = "Some Id";
  private String sampleTypeDirect = "TSS Direct Transactional";
  private String sampleTypeDistributor = "TSS Distributor Transactional";

  @BeforeEach
  void setUp() {
    sampleHeaderDirect = new HeaderDirect();
    sampleHeaderDirect.setId(sampleId);
    sampleHeaderDirect.setType(sampleTypeDirect);

    sampleHeaderDirectJpaEntity = new HeaderDirectJpaEntity();
    sampleHeaderDirectJpaEntity.setId(sampleId);
    sampleHeaderDirectJpaEntity.setType(sampleTypeDirect);

    sampleHeaderDistributor = new HeaderDistributor();
    sampleHeaderDistributor.setId(sampleId);
    sampleHeaderDistributor.setType(sampleTypeDistributor);

    sampleHeaderDistributorJpaEntity = new HeaderDistributorJpaEntity();
    sampleHeaderDistributorJpaEntity.setId(sampleId);
    sampleHeaderDistributorJpaEntity.setType(sampleTypeDistributor);

    headerRepositoryMock = mock(HeaderRepository.class);

    headerPersistenceAdapter = new HeaderPersistenceAdapter();
    MockitoAnnotations.initMocks(this);
  }

  @Test
  void persistHeaderDirectCorrectly() throws RepositoryException {
    when(headerRepositoryMock.create(sampleHeaderDirectJpaEntity))
        .thenReturn(sampleHeaderDirectJpaEntity);

    boolean isCreated = headerPersistenceAdapter.createHeader(sampleHeaderDirect);
    assertThat(isCreated).isTrue();
  }

  @Test
  void persistHeaderDistributorCorrectly() throws RepositoryException {
    when(headerRepositoryMock.create(sampleHeaderDistributorJpaEntity))
        .thenReturn(sampleHeaderDistributorJpaEntity);

    boolean isCreated = headerPersistenceAdapter.createHeader(sampleHeaderDistributor);
    assertThat(isCreated).isTrue();
  }

  @Test
  void persistHeaderDirectThrowsException() {
    when(headerRepositoryMock.create(sampleHeaderDirectJpaEntity)).thenReturn(null);

    assertThatThrownBy(
            () -> {
              headerPersistenceAdapter.createHeader(sampleHeaderDirect);
            })
        .isInstanceOf(RepositoryException.class)
        .hasMessage("Failed to create header");
  }

  @Test
  void persistHeaderDistributorThrowsException() {
    when(headerRepositoryMock.create(sampleHeaderDistributorJpaEntity)).thenReturn(null);

    assertThatThrownBy(
            () -> {
              headerPersistenceAdapter.createHeader(sampleHeaderDistributor);
            })
        .isInstanceOf(RepositoryException.class)
        .hasMessage("Failed to create header");
  }

  @Test
  void getHeaderDirectCorrectly() throws RepositoryException {
    when(headerRepositoryMock.read(sampleId, sampleTypeDirect))
        .thenReturn(sampleHeaderDirectJpaEntity);

    Header actualHeader = headerPersistenceAdapter.getHeader(sampleId, sampleTypeDirect);
    assertThat(actualHeader).isEqualToComparingFieldByField(sampleHeaderDirect);
  }

  @Test
  void getHeaderDistributorCorrectly() throws RepositoryException {
    when(headerRepositoryMock.read(sampleId, sampleTypeDistributor))
            .thenReturn(sampleHeaderDistributorJpaEntity);

    Header actualHeader = headerPersistenceAdapter.getHeader(sampleId, sampleTypeDistributor);
    assertThat(actualHeader).isEqualToComparingFieldByField(sampleHeaderDistributor);
  }

  @Test
  void failedToGetHeaderDirect() {
    assertThatThrownBy(() -> {
      when(headerRepositoryMock.read(sampleId, sampleTypeDirect))
              .thenReturn(null);
      headerPersistenceAdapter.getHeader(sampleId, sampleTypeDirect);
    }).isInstanceOf(RepositoryException.class).hasMessage("Failed to find header");
  }

  @Test
  void failedToGetHeaderDistributor() {
    assertThatThrownBy(() -> {
      when(headerRepositoryMock.read(sampleId, sampleTypeDistributor))
              .thenReturn(null);
      headerPersistenceAdapter.getHeader(sampleId, sampleTypeDistributor);
    }).isInstanceOf(RepositoryException.class).hasMessage("Failed to find header");
  }

  @Test
  void getAllHeadersCorrectly() throws RepositoryException {
    List<HeaderJpaEntity> sampleHeadersList = new ArrayList<>();
    sampleHeadersList.add(sampleHeaderDirectJpaEntity);
    sampleHeadersList.add(sampleHeaderDistributorJpaEntity);
    when(headerRepositoryMock.readAll()).thenReturn(sampleHeadersList);

    List<Header> actualHeadersList = headerPersistenceAdapter.getHeaders();
    assertThat(actualHeadersList.size()).isEqualTo(2);
  }

  @Test
  void failedToGetAllHeaders() {
    when(headerRepositoryMock.readAll()).thenReturn(new ArrayList<>());

    assertThatThrownBy(() -> {
      headerPersistenceAdapter.getHeaders();
    }).isInstanceOf(RepositoryException.class).hasMessage("Failed to find header");
  }
}
