package com.ibm.proposal.header.application.port.out;

import com.ibm.proposal.header.adapter.out.persistence.RepositoryException;
import com.ibm.proposal.header.domain.Header;

public interface CreateHeaderPort {

    boolean createHeader(Header header) throws RepositoryException;
}
