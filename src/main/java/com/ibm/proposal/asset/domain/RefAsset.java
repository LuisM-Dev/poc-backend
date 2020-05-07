package com.ibm.proposal.asset.domain;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RefAsset {
    private String installedCustomerNumber;
    private String type;
    private String model;
    private String assetSerialNumber;
    private String installDate;
    private String warrantyEndDate;
    private String sitePartyId;
}
