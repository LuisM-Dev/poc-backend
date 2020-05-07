package com.ibm.proposal.asset.application.port.out;

import com.ibm.proposal.asset.adapter.out.web.utils.RequestException;
import com.ibm.proposal.asset.domain.Asset;
import com.ibm.proposal.asset.domain.RefAsset;

import java.util.List;

public interface SearchRefAssetPort {

    List<RefAsset> searchRefAsset(Asset asset) throws RequestException;
}
