package com.ibm.proposal.header.adapter.out.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "header - direct")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HeaderDirectJpaEntity extends HeaderJpaEntity {
    @Column
    private String creditCheckAmount;
    @Column
    private Boolean customerPODriven;
    @Column
    private Boolean creditCheckIndicator;
    @Column
    private String eroExempted;
    @Column
    private String eroApproval;
    @Column
    private LocalDate creditCheckExpirationDate;
    @Column
    private String eroDocumentLink;
    @Column
    private Boolean retroactiveCheck;
    @Column
    private String retroDocument;
}
