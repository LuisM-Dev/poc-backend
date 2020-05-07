package com.ibm.proposal.header.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class HeaderDirect extends Header {
  public HeaderDirect() {
    super();
  }

  private String creditCheckAmount;
  private Boolean customerPODriven;
  private String eroExempted;
  private String eroApproval;
  private LocalDate creditCheckExpirationDate;
  private String eroDocumentLink;
  private Boolean retroactiveCheck;
  private String retroDocument;
}
