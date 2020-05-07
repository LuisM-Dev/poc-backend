package com.ibm.proposal.asset.adapter.in.web;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RefAssetWebModel {
    @Schema(example = "745687")
    private String installedCustomerNumber;
    @Schema(example = "3580")
    private String type;
    @Schema(example = "H6S")
    private String model;
    @Schema(example = "95633AB")
    private String assetSerialNumber;
    @Schema(example = "2019-09-20Z")
    private String installDate;
    @Schema(example = "2022-09-20Z")
    private String warrantyEndDate;
    @Schema(example = "123456")
    private String sitePartyId;
}
