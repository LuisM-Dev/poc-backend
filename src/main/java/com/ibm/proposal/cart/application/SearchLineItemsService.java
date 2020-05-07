package com.ibm.proposal.cart.application;

import com.ibm.proposal.cart.adapter.out.web.utils.RequestException;
import com.ibm.proposal.cart.application.port.in.SearchLineItemsUseCase;
import com.ibm.proposal.cart.application.port.out.CreateCartAssetPort;
import com.ibm.proposal.cart.application.port.out.GetAssetInformationPort;
import com.ibm.proposal.cart.application.port.out.GetHeaderInformationPort;
import com.ibm.proposal.cart.application.port.out.SearchLineItemsPort;
import com.ibm.proposal.cart.domain.Cart;
import com.ibm.proposal.cart.domain.Services;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@Getter
@Setter
public class SearchLineItemsService implements SearchLineItemsUseCase {

    @Inject
    private GetHeaderInformationPort getHeaderInformationPort;

    @Inject
    private GetAssetInformationPort getAssetInformationPort;

    @Inject
    private CreateCartAssetPort createCartAssetPort;

    @Inject
    private SearchLineItemsPort searchLineItemsPort;

    @Override
    public List<Services> searchLineItems(String proposalId, String proposalType) throws RequestException {
        Cart cart = getHeaderInformationPort.getHeaderInformation(proposalId, proposalType);
        cart.setAssets(getAssetInformationPort.getAssetInformation(proposalId, proposalType));
        return searchLineItemsPort.searchLineItems(cart);
    }

    void completeCartInformationWithHeaderInformation(Cart cart, Map<String,String> headerInformation) {
        cart.setCountry(headerInformation.get("leadCountry"));
        cart.setSalesChannel(headerInformation.get("salesChannel"));
        cart.setPriceReferenceDate(headerInformation.get("priceReferenceDate"));
        cart.setQuoteEndDate(headerInformation.get("quoteEndDate"));
        cart.setQuoteStartDate(headerInformation.get("quoteStartDate"));
    }
}
