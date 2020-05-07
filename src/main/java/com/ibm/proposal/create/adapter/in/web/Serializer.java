package com.ibm.proposal.create.adapter.in.web;

import com.google.gson.Gson;
import com.ibm.proposal.create.domain.Proposal;

class Serializer {

    private static Gson gson;

    private Serializer(){}

    static {
        gson = new Gson();
    }

    static String mapToJson(Proposal proposal) {
        return gson.toJson(proposal);
    }
}
