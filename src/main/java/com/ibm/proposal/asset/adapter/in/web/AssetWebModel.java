package com.ibm.proposal.asset.adapter.in.web;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AssetWebModel {
  @Schema(example = "123456")
  @NotEmpty(message = "ID cannot be empty")
  private String proposalId;

  @Pattern(message = "Proposal Type is invalid", regexp = "TSS Distributor Transactional|TSS Direct Transactional")
  @Schema(required = true, example = "TSS Distributor Transactional")
  private String proposalType;

  @Schema(example = "745687")
  @NotEmpty(message = "Field cannot be empty")
  private String installedCustomerNumber;

  @Schema(example = "3580")
  @NotEmpty(message = "Field cannot be empty")
  private String type;

  @Schema(example = "H6S")
  @NotEmpty(message = "Field cannot be empty")
  private String model;

  @Schema(example = "95633AB")
  @NotEmpty(message = "Field cannot be empty")
  private String assetSerialNumber;
}
