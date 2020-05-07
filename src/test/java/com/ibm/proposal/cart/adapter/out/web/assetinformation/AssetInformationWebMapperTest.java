package com.ibm.proposal.cart.adapter.out.web.assetinformation;

import com.ibm.proposal.cart.domain.Cart;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AssetInformationWebMapperTest {
    @Test
    void mapToWebRequestBody() {
        String sampleProposalId = "Some Proposal ID";
        String sampleProposalType = "Some Proposal Type";
        String expectedRequestBody = "{\"proposalId\":\"Some Proposal ID\",\"proposalType\":\"Some Proposal Type\"}";

        AssetInformationWebMapper assetInformationWebMapper = new AssetInformationWebMapper();
        String requestBody = assetInformationWebMapper.mapToWeb(sampleProposalId, sampleProposalType);
        assertThat(requestBody).isEqualTo(expectedRequestBody);
    }

    @Test
    void mapWebResponseToCartDomain() {
        String responseBody = "[{"
                + "\"installedCustomerNumber\":\"Some Customer Number 1\","
                + "\"type\":\"Some Type\","
                + "\"model\":\"Some Model 1\","
                + "\"assetSerialNumber\":\"Some Serial 1\","
                + "\"installDate\":\"Some Install Date 1\","
                + "\"warrantyEndDate\":\"Some Warranty End Date 1\""
                + "},"
                + "{"
                + "\"installedCustomerNumber\":\"Some Customer Number 2\","
                + "\"type\":\"Some Type\","
                + "\"model\":\"Some Model 2\","
                + "\"assetSerialNumber\":\"Some Serial 2\","
                + "\"installDate\":\"Some Install Date 2\","
                + "\"warrantyEndDate\":\"Some Warranty End Date 2\""
                + "}"
                + "]";

        Cart.Asset asset1 = new Cart.Asset();
        asset1.setWarrantyEndDate("Some Warranty End Date 1");
        asset1.setSerial("Some Serial 1");
        asset1.setInstallDate("Some Install Date 1");
        asset1.setProductId("Some Type-Some Model 1");
        asset1.setInstallAt("677778");
        asset1.setInstallAtSite("S027981386");

        Cart.Asset asset2 = new Cart.Asset();
        asset2.setWarrantyEndDate("Some Warranty End Date 2");
        asset2.setSerial("Some Serial 2");
        asset2.setInstallDate("Some Install Date 2");
        asset2.setProductId("Some Type-Some Model 2");
        asset2.setInstallAt("677778");
        asset2.setInstallAtSite("S027981386");

        List<Cart.Asset> cartAssets = new ArrayList<>();
        cartAssets.add(asset1);
        cartAssets.add(asset2);

        Cart expectedCart = new Cart();
        expectedCart.setAssets(cartAssets);

        AssetInformationWebMapper assetInformationWebMapper = new AssetInformationWebMapper();
        List<Cart.Asset> cartLineItems = assetInformationWebMapper.mapWebToDomain(responseBody);

        for(int i = 0; i<cartLineItems.size(); i++){
            assertThat(cartLineItems.get(i)).isEqualToComparingFieldByField(expectedCart.getAssets().get(i));
        }
    }
}
