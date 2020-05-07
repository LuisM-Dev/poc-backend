package com.ibm.proposal.asset.application;

import com.ibm.proposal.asset.adapter.out.persistence.RepositoryException;
import com.ibm.proposal.asset.adapter.out.web.utils.RequestException;
import com.ibm.proposal.asset.application.port.in.SearchRefAssetUseCase;
import com.ibm.proposal.asset.application.port.out.CreateRefAssetPort;
import com.ibm.proposal.asset.application.port.out.GetLeadCountryPort;
import com.ibm.proposal.asset.application.port.out.SearchRefAssetPort;
import com.ibm.proposal.asset.common.PersistenceAssetAdapter;
import com.ibm.proposal.asset.domain.Asset;
import com.ibm.proposal.asset.domain.RefAsset;

import javax.inject.Inject;
import java.util.List;

public class SearchRefAssetService implements SearchRefAssetUseCase {

  @Inject GetLeadCountryPort getLeadCountryPort;

  @Inject @PersistenceAssetAdapter SearchRefAssetPort searchRefAssetPort;

  @Inject CreateRefAssetPort createRefAssetPort;

  @Override
  public List<RefAsset> searchRefAsset(Asset asset) throws RequestException, RepositoryException {
    String countryCode = getLeadCountryPort.getLeadCountry(asset.getProposalId(), asset.getProposalType());
    List<RefAsset> refAssetsFound = searchAsset(asset, countryCode);
    createRefAssetPort.createRefAsset(asset, refAssetsFound);
    return refAssetsFound;
  }

  private List<RefAsset> searchAsset(Asset asset, String country) throws RequestException {
    asset.setCountryCode(country);
    return searchRefAssetPort.searchRefAsset(asset);
  }
}
