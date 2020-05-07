package com.ibm.proposal.asset.application.port.in;

import com.ibm.proposal.asset.domain.Asset;
import com.ibm.proposal.asset.domain.RefAsset;

public interface UpdateRefAssetUseCase {

    RefAsset updateRefAsset(Asset asset);
}
