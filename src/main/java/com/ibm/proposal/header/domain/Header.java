package com.ibm.proposal.header.domain;

import com.ibm.proposal.header.adapter.in.web.validator.EndDateTermLengthConstraint;
import com.ibm.proposal.header.adapter.in.web.validator.StartEndDateConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@StartEndDateConstraint
@EndDateTermLengthConstraint
public class Header {
  @NotEmpty(message = "ID is invalid")
  @Schema(required = true, example = "123456")
  private String id;
  @Pattern(message = "Proposal Type is invalid", regexp = "TSS Distributor Transactional|TSS Direct Transactional")
  @Schema(required = true, example = "TSS Distributor Transactional")
  private String type;
  @Pattern(message = "Lead Country is invalid", regexp = "AT|DE")
  @Schema(example = "AT")
  private String leadCountry;
  @NotEmpty(message = "Account is invalid")
  private String account;
  private String customer;
  private String billTo;
  private LocalDate startDate;
  private String termLengthMonths;
  private LocalDate endDate;
  private String billingFrequency;
  private LocalDate validUntilDate;
  private String billingPreference;
  private LocalDate pricingDate;
  private String description;
  private String fulfillmentChannel;
  private String customerReference;
  @Pattern(message = "Currency is invalid", regexp = "EUR - Euro|\\$ - Dollar")
  @Schema(example = "EUR")
  private String currency;
  private String tsaAgreement;
  private String owner;
  private String agreement;
  private String billingHistory;
  private Boolean creditCheckIndicator;
  private String createdBy;
  private String createdDate;
  private String currentConfigurationStatus;
  private String lastModifiedBy;
  private String approvalStage;
  private String salesChannel;
}
