package com.ibm.proposal.asset.application.port.in;

import com.ibm.proposal.asset.adapter.out.web.utils.RequestException;
import com.ibm.proposal.asset.domain.Asset;
import com.ibm.proposal.asset.domain.RefAsset;
import com.ibm.proposal.asset.adapter.out.persistence.RepositoryException;

import java.util.List;

public interface SearchRefAssetUseCase {

    List<RefAsset> searchRefAsset(Asset asset) throws RequestException, RepositoryException;
}
