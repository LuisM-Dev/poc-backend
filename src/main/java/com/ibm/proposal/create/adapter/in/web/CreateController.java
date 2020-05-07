package com.ibm.proposal.create.adapter.in.web;

import com.ibm.proposal.create.adapter.in.web.validator.WebValidator;
import com.ibm.proposal.create.adapter.out.persistence.RepositoryException;
import com.ibm.proposal.create.application.port.in.CreateNewProposalUseCase;
import com.ibm.proposal.create.domain.Proposal;
import com.ibm.proposal.create.domain.ProposalDistributor;
import com.ibm.proposal.create.adapter.in.web.validator.CreateErrorMessage;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("/proposal")
@Tag(
    name = "Create New Proposal",
    description = "Operations related to creating proposals")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CreateController {

  @Inject CreateNewProposalUseCase createNewProposalUseCase;

  @Inject WebValidator webValidator;

  @Path("/create")
  @POST
  @APIResponses(
      value = {
        @APIResponse(
            responseCode = "201",
            description = "List of available fields, default values and options.",
            content = @Content(schema = @Schema(implementation = ProposalDistributor.class))),
        @APIResponse(
            responseCode = "400",
            description = "Proposal Type is invalid",
            content =
                @Content(
                    mediaType = "application/json",
                    schema =
                        @Schema(
                            implementation = CreateErrorMessage.class))),
        @APIResponse(
            responseCode = "503",
            description = "Unable to create new proposal",
            content =
                @Content(
                    mediaType = "application/json",
                    schema =
                        @Schema(
                            implementation = CreateErrorMessage.class)))
      })
  @Operation(operationId = "create_post", summary = "Create proposal based on proposal type")
  public Response createNewProposal(CreateWebModel createWebModel) {
    CreateErrorMessage createErrorMessages = webValidator.validateProposalType(createWebModel);
    if (hasValidationErrors(createErrorMessages)) {
      return Response.status(Response.Status.BAD_REQUEST).entity(createErrorMessages).build();
    }
    try {
      Proposal createdProposal =
          createNewProposalUseCase.createNewProposal(createWebModel.getProposalType());
      return Response.status(Response.Status.CREATED)
          .entity(Serializer.mapToJson(createdProposal))
          .build();
    } catch (RepositoryException e) {
      createErrorMessages.createErrorMessage("repository", e.getMessage());
      return Response.status(Response.Status.SERVICE_UNAVAILABLE).entity(createErrorMessages).build();
    }
  }

  private boolean hasValidationErrors(CreateErrorMessage createErrorMessages) {
    return !createErrorMessages.getErrors().isEmpty();
  }
}
