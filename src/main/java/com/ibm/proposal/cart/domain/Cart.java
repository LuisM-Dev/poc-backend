package com.ibm.proposal.cart.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class Cart {
  @Expose private String country;

  @Expose
  @SerializedName(value = "quoteId")
  private String proposalId;

  private String proposalType;
  @Expose private String quoteStartDate;
  @Expose private String quoteEndDate;
  @Expose private String priceReferenceDate;
  @Expose private String salesChannel;
  @Expose private List<Asset> assets;

  @NoArgsConstructor
  @Getter
  @Setter
  public static class Asset {
    @Expose private String productId;
    @Expose private String serial;
    @Expose private String installDate;
    @Expose private String warrantyEndDate;
    @Expose private String installAt;
    @Expose private String installAtSite;
    @Expose private String addLocationBasedServices;
  }
}
