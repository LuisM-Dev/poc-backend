package com.ibm.proposal.cart.adapter.out.web.utils;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.HashMap;
import java.util.Map;

public class Request {

  private static final String AUTH_TOKEN = "Eq8rA63Cey68CiO2ZsXrCnjrt9vwJFF9R8EB74CJ";
  private final Map<String, String> requestHeaders;

  public Request() {
    requestHeaders = new HashMap<>();
    initRequestHeader();
  }

  private void initRequestHeader() {
    requestHeaders.put("Content-Type", "application/json");
    requestHeaders.put("Authorization", "Bearer " + AUTH_TOKEN);
  }

  public String postRequest(String requestUrl, String requestBody) throws RequestException {
    try {
      HttpResponse<String> response =
          Unirest.post(requestUrl).headers(requestHeaders).body(requestBody).asString();
      return validateResponse(response);
    } catch (UnirestException e) {
      return e.getMessage();
    }
  }

  private String validateResponse(HttpResponse<String> response) throws RequestException {
    if(isMissingAuthentication(response)){
      throw new RequestException("Authentication Expired");
    } else if(isResponseStatusNOK(response)) {
      if(isBodyEmpty(response)) {
        throw new RequestException(response.getStatusText());
      } else {
        throw new RequestException(response.getBody());
      }
    }
    return response.getBody();
  }

  private boolean isBodyEmpty(HttpResponse<String> response) {
    return response.getBody().isEmpty();
  }

  private boolean isResponseStatusNOK(HttpResponse<String> response) {
    return response.getStatus() != 200;
  }

  private boolean isMissingAuthentication(HttpResponse<String> response) {
    return response.getBody().contains("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
  }
}
