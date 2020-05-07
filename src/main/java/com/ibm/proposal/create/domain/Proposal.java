package com.ibm.proposal.create.domain;

import com.ibm.proposal.create.domain.utils.Utils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Proposal {

  @Schema(example = "123456")
  private String id;

  @Schema(example = "TSS Distributor Transactional")
  private String type;

  private List<ProposalField> fields;

  public Proposal(String type) {
    this.type = type;
  }

  public Proposal(String id, String type) {
    this.id = id;
    this.type = type;
  }

  public Proposal makeProposal() {
    if (type.equals("TSS Distributor Transactional"))
      return new ProposalDistributor(id, type);
    else return new ProposalDirect(id, type);
  }

  @Getter
  @Setter
  static class ProposalField {
    @Schema(example = "termLengthMonths")
    private String name;

    @Schema(example = "\"12\"")
    private String value;

    @Schema(example = "\"true\"")
    private String required;

    @Schema(example = "[\"12\",\"36\",\"60\"]")
    private String[] options;

    ProposalField(String name, String value) {
      this.name = name;
      this.value = value;
      this.required = "false";
      this.options = new String[] {};
    }

    ProposalField(String name, String value, String required) {
      this.name = name;
      this.value = value;
      this.required = required;
      this.options = new String[] {};
    }

    ProposalField(String name, String value, String[] options) {
      this.name = name;
      this.value = value;
      this.required = "false";
      this.options = options;
    }

    ProposalField(String name, String value, String required, String[] options) {
      this.name = name;
      this.value = value;
      this.required = required;
      this.options = options;
    }

    static List<ProposalField> createCommonFields(String fulfillmentChannel) {
      List<ProposalField> fields = new ArrayList<>();
      fields.add(ProposalField.defineRequiredField("leadCountry", "AT"));
      fields.add(ProposalField.defineRequiredField("account", ""));
      fields.add(ProposalField.defineField("customer", ""));
      fields.add(ProposalField.defineField("billTo", ""));
      fields.add(ProposalField.defineField("startDate", Utils.dateFieldFormatter(new Date())));
      fields.add(
          ProposalField.defineRequiredFieldWithOptions(
              "termLengthMonths", "36", Utils.createFieldOptions("12", "36", "60")));
      fields.add(ProposalField.defineField("endDate", Utils.dateFieldFormatter(new Date())));
      fields.add(
          ProposalField.defineFieldWithOptions(
              "billingFrequency", "Prepay", Utils.createFieldOptions("Quarterly", "Annually", "Prepay")));
      fields.add(ProposalField.defineField("validUntilDate", Utils.dateFieldFormatter(new Date())));
      fields.add(ProposalField.defineField("billingPreference", "Exact Cycle Time"));
      fields.add(ProposalField.defineField("pricingDate", Utils.dateFieldFormatter(new Date())));
      fields.add(ProposalField.defineField("description", ""));
      fields.add(ProposalField.defineField("fulfillmentChannel", fulfillmentChannel));
      fields.add(ProposalField.defineField("customerReference", ""));
      fields.add(
          ProposalField.defineRequiredFieldWithOptions(
              "currency", "EUR - Euro", Utils.createFieldOptions("EUR - Euro", "$ - Dollar")));
      fields.add(ProposalField.defineField("tsaAgreement", ""));
      fields.add(ProposalField.defineField("owner", ""));
      fields.add(ProposalField.defineField("agreement", ""));
      fields.add(
          ProposalField.defineFieldWithOptions(
              "billingHistory",
              "Billing History was provided within 18 months",
              Utils.createFieldOptions(
                  "Billing History was provided within 18 months",
                  "No Billing History was provided within 18 months")));
      fields.add(ProposalField.defineField("creditCheckAmount", ""));
      fields.add(ProposalField.defineField("creditCheckIndicator", "false"));
      fields.add(ProposalField.defineField("createdBy", ""));
      fields.add(ProposalField.defineField("currentConfigurationStatus", ""));
      fields.add(ProposalField.defineField("lastModifiedBy", ""));
      fields.add(ProposalField.defineField("approvalStage", ""));
      return fields;
    }

    static ProposalField defineField(String name, String defaultValue) {
      return new ProposalField(name, defaultValue);
    }

    static ProposalField defineRequiredField(String name, String defaultValue) {
      return new ProposalField(name, defaultValue, "true");
    }

    static ProposalField defineRequiredFieldWithOptions(
        String name, String defaultValue, String[] options) {
      return new ProposalField(name, defaultValue, "true", options);
    }

    static ProposalField defineFieldWithOptions(
        String name, String defaultValue, String[] options) {
      return new ProposalField(name, defaultValue, options);
    }
  }
}
