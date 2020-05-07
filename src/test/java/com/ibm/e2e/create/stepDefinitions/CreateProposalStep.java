package com.ibm.e2e.create.stepDefinitions;

import com.google.gson.JsonObject;
import com.ibm.proposal.create.adapter.in.web.CreateWebModel;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static com.google.gson.JsonParser.parseString;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class CreateProposalStep {

  private static String CREATE_PROPOSAL_REQUEST_URL = "http://localhost:9080/api/proposal/create";
  private CreateWebModel createWebModel;
  private RequestSpecification request;

  @Given("User wants to create new proposal {string}")
  public void user_wants_to_create_new_proposal(String type) {
    createWebModel = new CreateWebModel();
    createWebModel.setProposalType(type);
  }

  @When("User submits the request")
  public void user_submits_the_request() {
    request = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(createWebModel);
  }

  @Then("User receives available {string} with default values")
  public void user_receives_available_fields_with_default_values(String fields) {
    Response response = request.when().post(CREATE_PROPOSAL_REQUEST_URL);
    JsonObject jsonResponse = parseString(response.asString()).getAsJsonObject();
    JsonObject jsonExpectedResponse = parseString(fields).getAsJsonObject();
    assertThat(response.getStatusCode()).isEqualTo(200);
    assertThat(Integer.parseInt(jsonResponse.get("id").getAsString())).isGreaterThan(0);
    assertThat(jsonResponse.get("type")).isEqualTo(jsonExpectedResponse.get("type"));
  }

  @Then("User receives an error message {string}")
  public void user_receives_an_error_message(String message) {
    Response response = request.when().post(CREATE_PROPOSAL_REQUEST_URL);
    JsonObject jsonResponse = parseString(response.asString()).getAsJsonObject();
    assertThat(response.getStatusCode()).isEqualTo(400);
    assertThat(jsonResponse.getAsJsonObject("errors").get("proposalType").getAsString()).isEqualTo(message);
  }
}
