package com.ibm.proposal.cart.adapter.out.web.lineitem;

import com.ibm.proposal.cart.adapter.out.web.utils.Request;
import com.ibm.proposal.cart.adapter.out.web.utils.RequestException;
import com.ibm.proposal.cart.domain.Cart;
import com.ibm.proposal.cart.domain.Services;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LineItemWebAdapterTest {

  private String LINE_ITEM_REQUEST_URL =
      "https://tss-api-uat.quest-tss-435a25d3f409a5d1651084a621f22ab6-0001.us-south.containers.appdomain.cloud/api/services/assets?showPerformanceStatistics=false";
  private Cart sampleCart;
  private String sampleRequestBody;
  private String sampleResponseBody;
  private List<Services> expectedServices;
  @Mock
  private Request requestMock;
  @Mock
  private LineItemWebMapper lineItemWebMapperMock;
  @InjectMocks
  private LineItemWebAdapter lineItemWebAdapter;

  @BeforeEach
  void setUp() {
    sampleCart = new Cart();
    sampleRequestBody = "";
    sampleResponseBody = "";
    expectedServices = new ArrayList<>();
    lineItemWebMapperMock = mock(LineItemWebMapper.class);
    requestMock = mock(Request.class);
    lineItemWebAdapter = new LineItemWebAdapter();
    MockitoAnnotations.initMocks(this);
  }

  @Test
  void searchedLineItemCorrectly() throws Exception {

    when(lineItemWebMapperMock.mapEntityToWeb(sampleCart)).thenReturn(sampleRequestBody);
    when(lineItemWebMapperMock.mapWebToEntity(sampleResponseBody)).thenReturn(expectedServices);
    when(requestMock.postRequest(LINE_ITEM_REQUEST_URL, sampleRequestBody))
        .thenReturn(sampleResponseBody);

    List<Services> actualServices = lineItemWebAdapter.searchLineItems(sampleCart);
    assertThat(actualServices).isEqualTo(expectedServices);
  }

  @Test
  void searchLineItemException() {
    when(lineItemWebMapperMock.mapEntityToWeb(sampleCart)).thenReturn(sampleRequestBody);
    when(lineItemWebMapperMock.mapWebToEntity(sampleResponseBody)).thenReturn(expectedServices);
    assertThatThrownBy(
            () -> {
              when(requestMock.postRequest(LINE_ITEM_REQUEST_URL, sampleRequestBody))
                  .thenThrow(new RequestException("Some exception"));
              lineItemWebAdapter.searchLineItems(sampleCart);
            })
        .isInstanceOf(RequestException.class)
        .hasMessageContaining("Some exception");
  }
}
