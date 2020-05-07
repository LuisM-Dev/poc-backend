package com.ibm.proposal.asset.adapter.out.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "asset")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssetJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long assetId;
    @Column
    private String proposalId;
    @Column
    private String installedCustomerNumber;
    @Column
    private String type;
    @Column
    private String model;
    @Column
    private String assetSerialNumber;
    @Column
    private String soldTo;
    @Column
    private String recordType;
    @Column
    private String countryCode;
    @Column
    private String installDate;
    @Column
    private String warrantyEndDate;
}
