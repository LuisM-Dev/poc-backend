package com.ibm.proposal.create.adapter.in.web;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.validation.constraints.Pattern;

@NoArgsConstructor
@Setter
@Getter
public class CreateWebModel {
    @Schema(example = "TSS Distributor Transactional")
    @Pattern(message = "proposal type is invalid", regexp = "TSS Distributor Transactional|TSS Direct Transactional")
    private String proposalType;
}
