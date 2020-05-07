package com.ibm.proposal.asset.application.port.in;

import com.ibm.proposal.asset.domain.RefAsset;

import java.util.List;

public interface GetRefAssetUseCase {

    List<RefAsset> getRefAsset(String proposalId);
}
