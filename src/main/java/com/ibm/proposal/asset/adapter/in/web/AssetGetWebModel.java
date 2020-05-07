package com.ibm.proposal.asset.adapter.in.web;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AssetGetWebModel {
  @Schema(example = "123456")
  @NotEmpty(message = "ID cannot be empty")
  private String proposalId;
}
