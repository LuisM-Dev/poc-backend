package com.ibm.proposal.asset.adapter.in.web;

import com.ibm.proposal.asset.adapter.in.web.validator.ErrorMessage;
import com.ibm.proposal.asset.adapter.in.web.validator.WebValidator;
import com.ibm.proposal.asset.adapter.out.persistence.RepositoryException;
import com.ibm.proposal.asset.adapter.out.web.utils.RequestException;
import com.ibm.proposal.asset.application.port.in.GetRefAssetUseCase;
import com.ibm.proposal.asset.application.port.in.SearchRefAssetUseCase;
import com.ibm.proposal.asset.domain.RefAsset;
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

@RequestScoped
@Path("/proposal")
@Tag(name = "Asset Persistence", description = "Operations related to Asset persistence")
//@SecurityScheme(scheme = "apiAuth")
//@SecurityRequirement(name = "apiAuth" )
//@RolesAllowed({"AllRole"})
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AssetController {

  @Inject private SearchRefAssetUseCase searchRefAssetUseCase;

  @Inject private GetRefAssetUseCase getRefAssetUseCase;

  @Inject private WebValidator webValidator;

  @Inject private AssetWebMapper assetWebMapper;

  @Path("/asset")
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
                          description = "Search for Ref Asset",
                          content =
                          @Content(
                                  mediaType = "application/json",
                                  schema = @Schema(implementation = RefAssetWebModel.class, type = SchemaType.ARRAY)))
          })
  @Operation(operationId = "asset_post", summary = "Search reference asset")
  public Response searchAsset(AssetWebModel assetWebModel) {
    ErrorMessage errorMessages = webValidator.validateSearchRefAsset(assetWebModel);
    if (!errorMessages.getErrors().isEmpty()) {
      return Response.status(Response.Status.BAD_REQUEST).entity(errorMessages).build();
    }
    try {
      List<RefAsset> searchedRefAssets = searchRefAssetUseCase.searchRefAsset(assetWebMapper.mapToEntityDomain(assetWebModel));
      return Response.ok(assetWebMapper.mapToWebDomain(searchedRefAssets)).build();
    } catch (RequestException e) {
      errorMessages.createError("request", e.getMessage());
      return Response.status(Response.Status.BAD_REQUEST).entity(errorMessages).build();
    } catch (RepositoryException e) {
      errorMessages.createError("repository", e.getMessage());
      return Response.status(Response.Status.BAD_REQUEST).entity(errorMessages).build();
    }
  }
//
//  @Path("/asset")
//  @PUT
//  @Operation(operationId = "asset_put", summary = "Update asset")
//  public void updateAsset(Asset asset) {}

  @Path("/asset/information")
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
                          description = "Get for Ref Asset",
                          content =
                          @Content(
                                  mediaType = "application/json",
                                  schema = @Schema(implementation = RefAssetWebModel.class, type = SchemaType.ARRAY)))
          })
  @Operation(operationId = "asset_get", summary = "Get asset by proposal id")
  public Response getAsset(AssetGetWebModel assetGetWebModel) {
    ErrorMessage errorMessages = webValidator.validateGetRefAsset(assetGetWebModel);
    if (!errorMessages.getErrors().isEmpty()) {
      return Response.status(Response.Status.BAD_REQUEST).entity(errorMessages).build();
    }
    List<RefAsset> refAssets = getRefAssetUseCase.getRefAsset(assetGetWebModel.getProposalId());
    return Response.ok().entity(assetWebMapper.mapToWebDomain(refAssets)).build();
  }
}
