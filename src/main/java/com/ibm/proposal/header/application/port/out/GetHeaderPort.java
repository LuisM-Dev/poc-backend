package com.ibm.proposal.header.application.port.out;

import com.ibm.proposal.header.adapter.out.persistence.RepositoryException;
import com.ibm.proposal.header.domain.Header;

import java.util.List;

public interface GetHeaderPort {

    Header getHeader(String proposalId, String proposalType) throws RepositoryException;

    List<Header> getHeaders() throws RepositoryException;
}
