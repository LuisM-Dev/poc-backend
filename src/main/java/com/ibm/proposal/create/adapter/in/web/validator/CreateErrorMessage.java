package com.ibm.proposal.create.adapter.in.web.validator;

import lombok.Getter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import java.util.HashMap;
import java.util.Map;

@Getter
public class CreateErrorMessage {
  @Schema(example = "{\"error\": \"error message\"}")
  private Map<String, String> errors;

  public CreateErrorMessage() {
    errors = new HashMap<>();
  }

  public void createErrorMessage(String key, String value) {
    errors.put(key, value);
  }
}
