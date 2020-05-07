package com.ibm.proposal.asset.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Asset {
    private String proposalId;
    private String proposalType;
    private String installedCustomerNumber;
    private String type;
    private String model;
    private String assetSerialNumber;
    private String soldTo;
    private String recordType;
    private String countryCode;
    private String installDate;
    private String warrantyEndDate;
}
