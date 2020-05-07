package com.ibm.proposal.cart.adapter.in.web;

import com.ibm.proposal.cart.adapter.in.web.validator.ErrorMessage;
import com.ibm.proposal.cart.adapter.in.web.validator.WebValidator;
import com.ibm.proposal.cart.adapter.out.web.utils.RequestException;
import com.ibm.proposal.cart.application.port.in.SearchLineItemsUseCase;

import com.ibm.proposal.cart.domain.Services;
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

@NoArgsConstructor
@Setter
@RequestScoped
@Path("/proposal")
@Tag(name = "Cart Persistence", description = "Operations related to Cart persistence")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
// @SecurityScheme(scheme = "apiAuth")
// @SecurityRequirement(name = "apiAuth" )
// @RolesAllowed({"AllRole"})
public class CartController {

  @Inject private SearchLineItemsUseCase searchLineItemsUseCase;

  @Inject private WebValidator webValidator;

  @Path("/cart")
  @POST
  @APIResponses(
      value = {
        @APIResponse(
            responseCode = "400",
            description = "Invalid Request",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorMessage.class))),
        @APIResponse(
            responseCode = "200",
            description = "Successfully created cart with line items",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Services.class, type = SchemaType.ARRAY)))
      })
  @Operation(operationId = "cart_post", summary = "Create cart")
  public Response searchLineItems(CartGetWebModel cartGetWebModel) {
    System.out.println(cartGetWebModel.getProposalId() + " " + cartGetWebModel.getProposalType());
    ErrorMessage errorMessages = webValidator.validateCart(cartGetWebModel);
    System.out.println(errorMessages.getErrors());
    if (!errorMessages.getErrors().isEmpty()) {
      return Response.status(Response.Status.BAD_REQUEST).entity(errorMessages).build();
    }
    try {
      List<Services> searchedServices =
          searchLineItemsUseCase.searchLineItems(
              cartGetWebModel.getProposalId(), cartGetWebModel.getProposalType());
      return Response.ok().entity(searchedServices).build();
    } catch (RequestException e) {
      errorMessages.createError("requestError", e.getMessage());
      return Response.status(Response.Status.BAD_REQUEST).entity(errorMessages).build();
    }
  }

  //    @Path("/cart")
  //    @PUT
  //    @Operation(operationId = "cart_put", summary = "Update cart")
  //    public void updateLineItems(Cart cart) {}
  //
  //    @Path("/cart/{id}")
  //    @GET
  //    @Operation(operationId = "cart_get", summary = "Get cart by proposal id")
  //    public Cart getLineLineItems(@PathParam("id") String proposalId) {
  //        return null;
  //    }
}
