package com.ibm.proposal.cart.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class Services {
  private String status;
  private String product;
  private String platform;
  private String type;
  private String model;
  private String serialNumber;
  private String serviceLevel;
  private String startDate;
  private String endDate;
  private String netPrice;
  private String message;
  private List<SubLineItem> subLineItems;

  @NoArgsConstructor
  @Getter
  @Setter
  public static class SubLineItem {
    private String status;
    private String product;
    private String platform;
    private String type;
    private String model;
    private String serialNumber;
    private String serviceLevel;
    private String startDate;
    private String endDate;
    private String netPrice;
    private String message;
  }
}
