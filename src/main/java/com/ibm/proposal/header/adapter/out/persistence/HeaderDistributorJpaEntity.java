package com.ibm.proposal.header.adapter.out.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "header - distributor")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HeaderDistributorJpaEntity extends HeaderJpaEntity {
    @Column
    private String distributorAccount;
    @Column
    private String tier2Account;
    @Column
    private Boolean bpa2ndTierCheck;
    @Column
    private LocalDate bpa2ndTierExpireDate;
    @Column
    private Boolean retroactiveCheck;
    @Column
    private LocalDate creditCheckExpirationDate;
    @Column
    private String retroDocument;
    @Column
    private Boolean creditCheckIndicator;
    @Column
    private String creditCheckDocument;
}
