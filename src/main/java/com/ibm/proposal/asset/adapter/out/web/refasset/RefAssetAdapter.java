package com.ibm.proposal.asset.adapter.out.web.refasset;

import com.ibm.proposal.asset.adapter.out.web.utils.Request;
import com.ibm.proposal.asset.adapter.out.web.utils.RequestException;
import com.ibm.proposal.asset.application.port.out.SearchRefAssetPort;
import com.ibm.proposal.asset.common.PersistenceAssetAdapter;
import com.ibm.proposal.asset.domain.Asset;
import com.ibm.proposal.asset.domain.RefAsset;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.inject.Inject;
import java.util.List;

@PersistenceAssetAdapter
public class RefAssetAdapter implements SearchRefAssetPort {

    private static final String SEARCH_REF_ASSET_REQUEST_URL =
            "https://ra-api-uat.quest-tss-435a25d3f409a5d1651084a621f22ab6-0001.us-south.containers.appdomain.cloud/api/search/referenceasset";

    @Inject
    private Request request;

    @Inject
    private RefAssetMapper refAssetMapper;

    @Override
    public List<RefAsset> searchRefAsset(Asset asset) throws RequestException {
        String requestBody = refAssetMapper.mapToRequestBody(asset);
        String response = request.postRequest(SEARCH_REF_ASSET_REQUEST_URL,requestBody);
        return refAssetMapper.mapToRefAsset(response);
    }
}
