package com.ibm.proposal.header.adapter.in.web;

import com.ibm.proposal.header.adapter.out.persistence.RepositoryException;
import com.ibm.proposal.header.adapter.in.web.validator.ErrorMessage;
import com.ibm.proposal.header.adapter.in.web.validator.WebValidator;
import com.ibm.proposal.header.application.port.in.*;
import com.ibm.proposal.header.domain.Header;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

@RequestScoped
@Path("/proposal")
@Tag(name = "Header Persistence", description = "Operations related to Header persistence")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class HeaderController {

  @Inject CreateHeaderUseCase createHeaderUseCase;

  @Inject UpdateHeaderUseCase updateHeaderUseCase;

  @Inject GetHeaderUseCase getHeaderUseCase;

  @Inject GetHeaderInformationUseCase getHeaderInformationUseCase;

  @Inject WebValidator webValidator;

  @Path("/header")
  @POST
  @APIResponses(
      value = {
        @APIResponse(
            responseCode = "400",
            description = "Header fields failed validation",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorMessage.class))),
        @APIResponse(responseCode = "200", description = "All fields are valid")
      })
  @Operation(operationId = "header_post", summary = "Create proposal and proposal header")
  public Response createHeaderProposal(Header header) {
    ErrorMessage errorMessages = webValidator.validateCreateHeaderRequest(header);
    if (!errorMessages.getErrors().isEmpty()) {
      return Response.status(Response.Status.BAD_REQUEST).entity(errorMessages).build();
    }
    try {
      boolean isCreated = createHeaderUseCase.createProposal(header);
      return Response.ok(isCreated).build();
    } catch (RepositoryException e) {
      errorMessages.createMessage("repository", e.getMessage());
      return Response.status(Response.Status.BAD_REQUEST).entity(errorMessages).build();
    }
  }

  @Path("/header/information")
  @POST
  @APIResponses(
      value = {
        @APIResponse(
            responseCode = "400",
            description = "Failed Request",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorMessage.class))),
        @APIResponse(
            responseCode = "200",
            description = "Header main Information",
            content = @Content(mediaType = "application/json"))
      })
  @Operation(operationId = "header_information_get", summary = "Get Header Information")
  public Response getHeaderInformation(HeaderGetWebModel headerGetWebModel) {
    ErrorMessage errorMessages = webValidator.validateGetRequest(headerGetWebModel);
    if (!errorMessages.getErrors().isEmpty()) {
      return Response.status(Response.Status.BAD_REQUEST).entity(errorMessages).build();
    }
    try {
      Map<String, String> headerInformation =
          getHeaderInformationUseCase.getHeaderInformation(
              headerGetWebModel.getProposalId(), headerGetWebModel.getProposalType());
      return Response.ok().entity(headerInformation).build();
    } catch (RepositoryException e) {
      errorMessages.createMessage("repository", e.getMessage());
      return Response.status(Response.Status.BAD_REQUEST).entity(errorMessages).build();
    }
  }

  @Path("/header/all")
  @GET
  @APIResponses(
      value = {
        @APIResponse(
            responseCode = "400",
            description = "Failed Request",
            content = @Content(mediaType = "application/json")),
        @APIResponse(
            responseCode = "200",
            description = "All Headers",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Header.class, type = SchemaType.ARRAY)))
      })
  @Operation(operationId = "header_get_all", summary = "Get all proposal headers")
  public Response getAllHeaderProposal() {
    try {
      List<Header> header = getHeaderUseCase.getHeaders();
      return Response.ok().entity(header).build();
    } catch (RepositoryException e) {
      ErrorMessage errorMessages = new ErrorMessage();
      errorMessages.createMessage("repository", e.getMessage());
      return Response.status(Response.Status.BAD_REQUEST).entity(errorMessages).build();
    }
  }

  @Path("/header/id")
  @POST
  @APIResponses(
      value = {
        @APIResponse(
            responseCode = "400",
            description = "Failed Request",
            content = @Content(mediaType = "application/json")),
        @APIResponse(
            responseCode = "200",
            description = "All Headers",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Header.class)))
      })
  @Operation(operationId = "header_get_single", summary = "Get specific proposal")
  public Response getHeaderProposal(HeaderGetWebModel headerGetWebModel) {
    ErrorMessage errorMessages = webValidator.validateGetRequest(headerGetWebModel);
    if (!errorMessages.getErrors().isEmpty()) {
      return Response.status(Response.Status.BAD_REQUEST).entity(errorMessages).build();
    }
    try{
      Header header =
              getHeaderUseCase.getHeader(
                      headerGetWebModel.getProposalId(), headerGetWebModel.getProposalType());
      return Response.ok().entity(header).build();
    } catch (RepositoryException e) {
      errorMessages.createMessage("repository", e.getMessage());
      return Response.status(Response.Status.BAD_REQUEST).entity(errorMessages).build();
    }
  }
}
