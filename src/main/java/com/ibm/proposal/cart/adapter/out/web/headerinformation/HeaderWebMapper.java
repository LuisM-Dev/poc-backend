package com.ibm.proposal.cart.adapter.out.web.headerinformation;

import com.google.gson.Gson;
import com.ibm.proposal.cart.domain.Cart;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

class HeaderWebMapper {

    String mapEntityToWeb(String proposalId, String proposalType) {
        RequestModel requestModel = new RequestModel(proposalId, proposalType);
        return new Gson().toJson(requestModel);
    }

    Cart mapWebToEntity(String responseBody) {
        ResponseModel responseModel = new Gson().fromJson(responseBody, ResponseModel.class);
        Cart cart = new Cart();
        cart.setCountry(responseModel.leadCountry);
        cart.setQuoteStartDate(responseModel.startDate);
        cart.setQuoteEndDate(responseModel.endDate);
        cart.setSalesChannel(responseModel.salesChannel);
        cart.setPriceReferenceDate(responseModel.pricingDate);
        return cart;
    }

    @AllArgsConstructor
    private static class RequestModel {
        private String proposalId;
        private String proposalType;
    }

    @AllArgsConstructor
    private static class ResponseModel {
        private String startDate;
        private String endDate;
        private String leadCountry;
        private String pricingDate;
        private String salesChannel;
    }

}
