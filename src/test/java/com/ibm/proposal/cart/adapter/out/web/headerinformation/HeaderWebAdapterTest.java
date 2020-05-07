package com.ibm.proposal.cart.adapter.out.web.headerinformation;

import com.ibm.proposal.cart.adapter.out.web.utils.Request;
import com.ibm.proposal.cart.adapter.out.web.utils.RequestException;
import com.ibm.proposal.cart.domain.Cart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class HeaderWebAdapterTest {

  private String HEADER_INFO_REQUEST_URL = "http://localhost:9080/api/proposal/header/information";
  private String sampleProposalId;
  private String sampleProposalType;
  private String sampleRequestBody;
  private String sampleResponseBody;
  @Mock private Request requestMock;
  @Mock private HeaderWebMapper headerWebMapperMock;
  @InjectMocks private HeaderWebAdapter headerWebAdapter;

  @BeforeEach
  void setUp() {
    sampleProposalId = "Some Id";
    sampleProposalType = "Some Proposal Type";
    sampleRequestBody = "";
    sampleResponseBody = "";
    requestMock = mock(Request.class);
    headerWebMapperMock = mock(HeaderWebMapper.class);
    headerWebAdapter = new HeaderWebAdapter();
    MockitoAnnotations.initMocks(this);
  }

  @Test
  void collectHeaderInfoCorrectly() throws RequestException {
    when(requestMock.postRequest(HEADER_INFO_REQUEST_URL, sampleRequestBody))
        .thenReturn(sampleResponseBody);
    when(headerWebMapperMock.mapEntityToWeb(sampleProposalId, sampleProposalType))
        .thenReturn(sampleRequestBody);
    when(headerWebMapperMock.mapWebToEntity(sampleResponseBody)).thenReturn(new Cart());

    Cart actualCart = headerWebAdapter.getHeaderInformation(sampleProposalId, sampleProposalType);
    assertThat(actualCart).isNotNull();
  }

  @Test
  void collectHeaderException() {
    when(headerWebMapperMock.mapEntityToWeb("Some Id", "Some Proposal Type"))
        .thenReturn(sampleRequestBody);
    assertThatThrownBy(
            () -> {
              when(requestMock.postRequest(HEADER_INFO_REQUEST_URL, sampleRequestBody))
                  .thenThrow(new RequestException("Request exception"));
              headerWebAdapter.getHeaderInformation(sampleProposalId, sampleProposalType);
            })
        .isInstanceOf(RequestException.class)
        .hasMessage("Request exception");
  }
}
