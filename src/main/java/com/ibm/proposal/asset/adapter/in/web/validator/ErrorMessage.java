package com.ibm.proposal.asset.adapter.in.web.validator;

import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class ErrorMessage {
    @Schema(example = "{\"proposalId\": \"Proposal ID is invalid\"}")
    private Map<String, String> errors;

    public ErrorMessage() {
        errors = new HashMap<>();
    }

    public void createError(String key, String value) {
        errors.put(key, value);
    }
}
