package com.ibm.proposal.header.application;

import com.ibm.proposal.header.adapter.out.persistence.RepositoryException;
import com.ibm.proposal.header.application.port.out.GetHeaderPort;
import com.ibm.proposal.header.domain.Header;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetHeaderServiceTest {

    private String sampleId;
    @Mock private GetHeaderPort getHeaderPortMock;
    @InjectMocks private GetHeaderService getHeaderService;

    @BeforeEach
    void setUp() {
        sampleId = "Some Id";
        getHeaderPortMock = mock(GetHeaderPort.class);
        getHeaderService = new GetHeaderService();
        MockitoAnnotations.initMocks(this);
      }

    @Test
    void getProposalHeaderDirect() throws RepositoryException {
        String sampleType = "TSS Direct Transactional";

        when(getHeaderPortMock.getHeader(sampleId, sampleType)).thenReturn(new Header());

        Header foundHeader = getHeaderService.getHeader(sampleId, sampleType);
        assertThat(foundHeader).isNotNull();
    }

    @Test
    void getProposalHeaderDistributor() throws RepositoryException {
        String sampleType = "TSS Distributor Transactional";

        when(getHeaderPortMock.getHeader(sampleId, sampleType)).thenReturn(new Header());

        Header foundHeader = getHeaderService.getHeader(sampleId, sampleType);
        assertThat(foundHeader).isNotNull();
    }

    @Test
    void getAllProposalsHeaders() throws RepositoryException {
        List<Header> expectedProposals = new ArrayList<>();
        expectedProposals.add(new Header());
        expectedProposals.add(new Header());

        when(getHeaderPortMock.getHeaders()).thenReturn(expectedProposals);

        List<Header> allProposals = getHeaderService.getHeaders();
        assertThat(allProposals).hasSize(2);
    }
}
