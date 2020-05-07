package com.ibm.proposal.header.adapter.out.persistence;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;
import java.util.Date;

@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class HeaderJpaEntity {
    @Id
    @Column
    private String id;
    @Column
    private String type;
    @Column
    private String leadCountry;
    @Column
    private String account;
    @Column
    private String customer;
    @Column
    private String billTo;
    @Column
    private LocalDate startDate;
    @Column
    private String termLengthMonths;
    @Column
    private LocalDate endDate;
    @Column
    private String billingFrequency;
    @Column
    private LocalDate validUntilDate;
    @Column
    private String billingPreference;
    @Column
    private LocalDate pricingDate;
    @Column
    private String description;
    @Column
    private String fulfillmentChannel;
    @Column
    private String customerReference;
    @Column
    private String currency;
    @Column
    private String tsaAgreement;
    @Column
    private String owner;
    @Column
    private String agreement;
    @Column
    private String billingHistory;
    @Column
    private String createdBy;
    @Column
    private String createdDate;
    @Column
    private String currentConfigurationStatus;
    @Column
    private String lastModifiedBy;
    @Column
    private String approvalStage;
}
