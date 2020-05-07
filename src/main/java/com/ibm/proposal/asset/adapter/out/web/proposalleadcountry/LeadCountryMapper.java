package com.ibm.proposal.asset.adapter.out.web.proposalleadcountry;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Getter;
public class LeadCountryMapper {

    String mapToRequestBody(String proposalId, String proposalType) {
        RequestModel requestModel = new RequestModel(proposalId, proposalType);
        return new Gson().toJson(requestModel);
    }

    String mapToLeadCountry(String requestResponse) {
        ResponseModel responseModel = new Gson().fromJson(requestResponse, ResponseModel.class);
        return responseModel.getLeadCountry();
    }

    @AllArgsConstructor
    @Getter
    private static class RequestModel {
        private String proposalId;
        private String proposalType;
    }

    @AllArgsConstructor
    @Getter
    private static class ResponseModel {
        private String leadCountry;
    }
}
