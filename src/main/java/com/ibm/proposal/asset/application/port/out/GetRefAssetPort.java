package com.ibm.proposal.asset.application.port.out;

import com.ibm.proposal.asset.domain.RefAsset;

import java.util.List;

public interface GetRefAssetPort {

    List<RefAsset> getRefAsset(String id);
}
