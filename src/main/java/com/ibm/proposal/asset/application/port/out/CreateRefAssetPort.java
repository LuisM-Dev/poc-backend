package com.ibm.proposal.asset.application.port.out;

import com.ibm.proposal.asset.domain.Asset;
import com.ibm.proposal.asset.domain.RefAsset;
import com.ibm.proposal.asset.adapter.out.persistence.RepositoryException;

import java.util.List;

public interface CreateRefAssetPort {

    boolean createRefAsset(Asset asset, List<RefAsset> refAssets) throws RepositoryException;
}
