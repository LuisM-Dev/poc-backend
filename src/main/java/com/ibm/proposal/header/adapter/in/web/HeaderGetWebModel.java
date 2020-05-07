package com.ibm.proposal.header.adapter.in.web;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HeaderGetWebModel {
    @NotEmpty(message = "ID is invalid")
    @Schema(required = true, example = "123456")
    private String proposalId;
    @Pattern(message = "Proposal Type is invalid", regexp = "TSS Distributor Transactional|TSS Direct Transactional")
    @Schema(required = true, example = "TSS Distributor Transactional")
    private String proposalType;
}
