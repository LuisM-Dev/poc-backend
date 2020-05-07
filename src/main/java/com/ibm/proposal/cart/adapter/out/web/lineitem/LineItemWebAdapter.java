package com.ibm.proposal.cart.adapter.out.web.lineitem;

import com.ibm.proposal.cart.adapter.out.web.utils.Request;
import com.ibm.proposal.cart.adapter.out.web.utils.RequestException;
import com.ibm.proposal.cart.application.port.out.SearchLineItemsPort;
import com.ibm.proposal.cart.domain.Cart;
import com.ibm.proposal.cart.domain.Services;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.inject.Inject;
import java.util.List;

public class LineItemWebAdapter implements SearchLineItemsPort {

    private static final String LINE_ITEM_REQUEST_URL = "https://tss-api-uat.quest-tss-435a25d3f409a5d1651084a621f22ab6-0001.us-south.containers.appdomain.cloud/api/services/assets?showPerformanceStatistics=false";

    @Inject
    private LineItemWebMapper lineItemWebMapper;

    @Inject
    private Request request;

    public List<Services> searchLineItems(Cart cart) throws RequestException {
        String requestBody = lineItemWebMapper.mapEntityToWeb(cart);
        String responseBody = request.postRequest(LINE_ITEM_REQUEST_URL,requestBody);
        return lineItemWebMapper.mapWebToEntity(responseBody);
    }
}
