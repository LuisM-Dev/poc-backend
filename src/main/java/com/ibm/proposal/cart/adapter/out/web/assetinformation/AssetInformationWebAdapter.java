package com.ibm.proposal.cart.adapter.out.web.assetinformation;

import com.ibm.proposal.cart.adapter.out.web.utils.Request;
import com.ibm.proposal.cart.adapter.out.web.utils.RequestException;
import com.ibm.proposal.cart.application.port.out.GetAssetInformationPort;
import com.ibm.proposal.cart.domain.Cart;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.inject.Inject;
import java.util.List;

public class AssetInformationWebAdapter implements GetAssetInformationPort {

    private static final String ASSET_INFO_REQUEST_URL = "http://localhost:9080/api/proposal/asset/information";

    @Inject
    private AssetInformationWebMapper assetInformationWebMapper;

    @Inject
    private Request request;

    @Override
    public List<Cart.Asset> getAssetInformation(String proposalId, String proposalType) throws RequestException {
        String requestBody = assetInformationWebMapper.mapToWeb(proposalId, proposalType);
        String responseBody = request.postRequest(ASSET_INFO_REQUEST_URL, requestBody);
        return assetInformationWebMapper.mapWebToDomain(responseBody);
    }
}
