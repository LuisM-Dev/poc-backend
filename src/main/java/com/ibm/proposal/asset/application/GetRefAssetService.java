package com.ibm.proposal.asset.application;

import com.ibm.proposal.asset.application.port.in.GetRefAssetUseCase;
import com.ibm.proposal.asset.application.port.out.GetRefAssetPort;
import com.ibm.proposal.asset.domain.RefAsset;
import lombok.Setter;

import javax.inject.Inject;
import java.util.List;

public class GetRefAssetService implements GetRefAssetUseCase {

    @Setter
    @Inject
    private GetRefAssetPort getRefAssetPort;

    @Override
    public List<RefAsset> getRefAsset(String id) {
        return getRefAssetPort.getRefAsset(id);
    }
}
