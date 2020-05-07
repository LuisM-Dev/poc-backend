package com.ibm.proposal.cart.adapter.in.web.validator;

import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class ErrorMessage {

    @Schema(example = "{\"proposalId\": \"Proposal ID is invalid\"}")
    private Map<String, String> errors;

    public ErrorMessage() {
        errors = new HashMap<>();
    }

    public void createError(String errorName, String errorMessage) {
        errors.put(errorName,errorMessage);
    }
}
