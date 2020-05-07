package com.ibm.proposal.header.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class HeaderDistributor extends Header {
    public HeaderDistributor() {
        super();
    }

    private String distributorAccount;
    private String tier2Account;
    private Boolean bpa2ndTierCheck;
    private LocalDate bpa2ndTierExpireDate;
    private Boolean retroactiveCheck;
    private LocalDate creditCheckExpirationDate;
    private String retroDocument;
    private String creditCheckDocument;
}
