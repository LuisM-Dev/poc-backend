package com.ibm.proposal.header.application;

import com.ibm.proposal.header.adapter.out.persistence.RepositoryException;
import com.ibm.proposal.header.application.port.out.CreateHeaderPort;
import com.ibm.proposal.header.domain.Header;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class CreateHeaderServiceTest {

    @Mock private CreateHeaderPort createHeaderPortMock;
    @InjectMocks private CreateHeaderService createHeaderService;

  @BeforeEach
  void setUp() {
      createHeaderPortMock = mock(CreateHeaderPort.class);
      createHeaderService = new CreateHeaderService();
      MockitoAnnotations.initMocks(this);
  }

    @Test
    void createHeaderSuccessfully() throws RepositoryException {
        Header sampleHeader = new Header();

        when(createHeaderPortMock.createHeader(sampleHeader)).thenReturn(true);
        Boolean isCreated = createHeaderService.createProposal(sampleHeader);
        assertThat(isCreated).isTrue();
    }
}
