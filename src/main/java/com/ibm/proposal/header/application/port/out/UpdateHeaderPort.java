package com.ibm.proposal.header.application.port.out;

import com.ibm.proposal.header.domain.Header;

public interface UpdateHeaderPort {

    Header updateHeader(Header proposalHeader);
}
