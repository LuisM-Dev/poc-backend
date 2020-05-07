package com.ibm.proposal.create.domain;

import com.ibm.proposal.create.domain.utils.Utils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ProposalDistributor extends Proposal {

    ProposalDistributor(String id, String type) {
        super(id, type);
        super.setFields(createFieldList());
    }

    private List<ProposalField> createFieldList() {
        List<ProposalField> fields = ProposalField.createCommonFields("Distributor");
        fields.add(ProposalField.defineField("distributorAccount", ""));
        fields.add(ProposalField.defineField("tier2Account", ""));
        fields.add(ProposalField.defineField("bpa2ndTierCheck", "false"));
        fields.add(ProposalField.defineField("bpa2ndTierExpireDate", Utils.dateFieldFormatter(new Date())));
        fields.add(ProposalField.defineField("retroactiveCheck","false"));
        fields.add(ProposalField.defineField("creditCheckExpirationDate", Utils.dateFieldFormatter(new Date())));
        fields.add(ProposalField.defineField("retroDocument", ""));
        fields.add(ProposalField.defineField("creditCheckDocument", ""));
        return fields;
    }
}
