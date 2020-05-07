package com.ibm.proposal.cart.adapter.out.web.assetinformation;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.ibm.proposal.cart.domain.Cart;
import lombok.AllArgsConstructor;

import java.lang.reflect.Type;
import java.util.List;

public class AssetInformationWebMapper {
  public List<Cart.Asset> mapWebToDomain(String responseBody) {

    GsonBuilder gsonBuilder = new GsonBuilder();
    JsonDeserializer<Cart.Asset> deserializer =
        new JsonDeserializer<Cart.Asset>() {
          @Override
          public Cart.Asset deserialize(
              JsonElement json, Type typeOfT, JsonDeserializationContext context)
              throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            Cart.Asset cartAsset = new Cart.Asset();
            cartAsset.setWarrantyEndDate(jsonObject.get("warrantyEndDate").getAsString());
            cartAsset.setInstallDate(jsonObject.get("installDate").getAsString());
            cartAsset.setProductId(
                jsonObject.get("type").getAsString() + "-" + jsonObject.get("model").getAsString());
            cartAsset.setSerial(jsonObject.get("assetSerialNumber").getAsString());
            cartAsset.setInstallAtSite("S027981386");
            cartAsset.setInstallAt("677778");
            return cartAsset;
          }
        };
    gsonBuilder.registerTypeAdapter(Cart.Asset.class, deserializer);
    Gson custom = gsonBuilder.create();
    return custom.fromJson(responseBody, new TypeToken<List<Cart.Asset>>() {}.getType());
  }

    public String mapToWeb(String proposalId, String proposalType) {
        return new Gson().toJson(new RequestModel(proposalId,proposalType));
    }

    @AllArgsConstructor
    private static class RequestModel {
      private String proposalId;
      private String proposalType;
    }
}
