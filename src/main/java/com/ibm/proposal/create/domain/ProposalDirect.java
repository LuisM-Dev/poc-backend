package com.ibm.proposal.create.domain;

import com.ibm.proposal.create.domain.utils.Utils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ProposalDirect extends Proposal {

    private ProposalField customerPODriven;
    private ProposalField creditCheckDocument;
    private ProposalField creditCheckIndicator;
    private ProposalField eroExempted;
    private ProposalField eroApproval;
    private ProposalField creditCheckExpirationDate;
    private ProposalField eroDocumentLink;
    private ProposalField retroactiveCheck;
    private ProposalField retroDocument;

    ProposalDirect(String id, String type) {
        super(id, type);
        super.setFields(createFieldList());
    }

    private List<ProposalField> createFieldList() {
        List<ProposalField> fields = ProposalField.createCommonFields("Direct");
        fields.add(ProposalField.defineField("customerPODriven", "true"));
        fields.add(ProposalField.defineField("creditCheckAmount", ""));
        fields.add(ProposalField.defineField("eroExempted", ""));
        fields.add(ProposalField.defineField("eroApproval", ""));
        fields.add(ProposalField.defineField("creditCheckExpirationDate", Utils.dateFieldFormatter(new Date())));
        fields.add(ProposalField.defineField("eroDocumentLink", ""));
        fields.add(ProposalField.defineField("retroactiveCheck","false"));
        fields.add(ProposalField.defineField("retroDocument", ""));
        return fields;
    }
}
