package com.ibm.proposal.header.adapter.in.web.validator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.jboss.logging.Message;

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

  public void createMessage(String key, String value) {
    errors.put(key, value);
  }
}
