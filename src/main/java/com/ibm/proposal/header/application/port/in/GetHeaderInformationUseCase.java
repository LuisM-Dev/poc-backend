package com.ibm.proposal.header.application.port.in;

import com.ibm.proposal.header.adapter.out.persistence.RepositoryException;

import java.util.Map;

public interface GetHeaderInformationUseCase {

    Map<String,String> getHeaderInformation(String proposalId, String proposalType) throws RepositoryException;
}
