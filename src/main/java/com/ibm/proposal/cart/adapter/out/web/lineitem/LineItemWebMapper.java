package com.ibm.proposal.cart.adapter.out.web.lineitem;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ibm.proposal.cart.domain.Cart;
import com.ibm.proposal.cart.domain.Services;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LineItemWebMapper {
  public String mapEntityToWeb(Cart cart) {
    return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().toJson(cart);
  }

  List<Services> mapWebToEntity(String responseBody) {
    ResponseModel responseModel = new Gson().fromJson(responseBody, ResponseModel.class);
    return createLineItems(responseModel);
  }

  private List<Services> createLineItems(ResponseModel response) {
    List<ResponseModel.LineItem> lineItems = response.getLineItems();
    List<ResponseModel.Message> lineItemsMessages = response.getMessages();
    if (lineItems != null) {
      return IntStream.range(0, lineItems.size()).mapToObj(
              lineItem -> {
                Services servicesLineItem = new Services();
                ResponseModel.LineItem item = lineItems.get(lineItem);
                ResponseModel.DisplayOutput displayOutput = lineItems.get(lineItem).getDisplayOutput();
                ResponseModel.Message message = lineItemsMessages.get(lineItem);

                servicesLineItem.setStatus(parseField(message.getMessageLevel()));
                servicesLineItem.setProduct(parseField(item.getHardware().getProductId()));
                servicesLineItem.setPlatform(parseField(item.getPlatform()));
                servicesLineItem.setType(parseItemType(displayOutput.getReferenceAssetId()));
                servicesLineItem.setModel(parseItemModel(displayOutput.getReferenceAssetId()));
                servicesLineItem.setServiceLevel(parseField(displayOutput.getServiceLevel()));
                servicesLineItem.setSerialNumber(parseField(displayOutput.getSerial()));
                servicesLineItem.setStartDate(parseField(item.getServiceStartDate()));
                servicesLineItem.setEndDate(parseField(item.getServiceEndDate()));
                servicesLineItem.setNetPrice(calculateItemNetPrice(item.getAnnualListPrice(), displayOutput.getQuantity()));
                servicesLineItem.setMessage(parseMessage(message.getMessageLevel(),message.getMessageText()));
                servicesLineItem.setSubLineItems(createSubLineItems(item));
                return servicesLineItem;
              })
          .collect(Collectors.toList());
    }
    return new ArrayList<>();
  }

  private List<Services.SubLineItem> createSubLineItems(ResponseModel.LineItem lineItem) {
    List<ResponseModel.SubLineItem> subLineItems = lineItem.getSubLineItems();
    List<ResponseModel.Message> subLineItemsMessages = lineItem.getMessages();
    if (subLineItems != null) {
      return IntStream.range(0, subLineItems.size()).mapToObj(
              subLineItem -> {
                Services.SubLineItem servicesSubLineItem = new Services.SubLineItem();
                ResponseModel.SubLineItem item = subLineItems.get(subLineItem);
                ResponseModel.SubLineItemSoftware software = subLineItems.get(subLineItem).getSoftware();
                ResponseModel.DisplayOutput displayOutput = subLineItems.get(subLineItem).getDisplayOutput();
                ResponseModel.Message message = subLineItemsMessages.get(subLineItem);

                servicesSubLineItem.setStatus(parseField(message.getMessageLevel()));
                servicesSubLineItem.setProduct(parseField(software.getProductId()));
                servicesSubLineItem.setPlatform(parseField(lineItem.getPlatform()));
                servicesSubLineItem.setServiceLevel(parseField(lineItem.getDisplayOutput().getServiceLevel()));
                servicesSubLineItem.setStartDate(parseField(item.getServiceStartDate()));
                servicesSubLineItem.setEndDate(parseField(item.getServiceEndDate()));
                servicesSubLineItem.setNetPrice(calculateItemNetPrice(item.getAnnualListPrice(),item.getQuantity()));
                servicesSubLineItem.setType(parseItemType(displayOutput.getReferenceAssetId()));
                servicesSubLineItem.setModel(parseItemModel(displayOutput.getReferenceAssetId()));
                servicesSubLineItem.setSerialNumber(parseField(displayOutput.getSerial()));
                servicesSubLineItem.setMessage(parseMessage(message.getMessageLevel(),message.getMessageText()));
                return servicesSubLineItem;
              }).collect(Collectors.toList());
    }
    return new ArrayList<>();
  }

  private String parseItemType(String assetReferenceId) {
    return parseField(assetReferenceId).split("-")[0];
  }

  private String parseItemModel(String assetReferenceId) {
    return parseField(assetReferenceId).split("-")[1];
  }

  private String calculateItemNetPrice(String annualPrice, String quantity) {
    if(isNullOrEmpty(annualPrice) || isNullOrEmpty(quantity)) {
      return "0";
    }
    return Double.toString(Double.parseDouble(annualPrice) * Double.parseDouble(quantity));
  }

  private String parseField(String str) {
    if(isNullOrEmpty(str)) {
      return "";
    }
    return str;
  }

  private String parseMessage(String messageLevel, String messageText) {
    return parseField(messageLevel) + ": " + parseField(messageText);
  }

  private boolean isNullOrEmpty(String str) {
    return str == null || str.trim().isEmpty();
  }

  static class RequestModel {
    private String country;
    private String quoteId;
    private String quoteStartDate;
    private String quoteEndDate;
    private String priceReferenceDate;
    private String salesChannel;
    private List<Asset> assets;

    static class Asset {
      private String productId;
      private String serial;
      private String installDate;
      private String warrantyEndDate;
      private String installAt;
      private String installAtSite;
      private String addLocationBasedServices;
    }
  }

  @NoArgsConstructor
  @Getter
  @Setter
  static class ResponseModel {
    private String quoteId;
    private String cartId;
    private String country;
    private List<LineItem> lineItems;
    private List<Message> messages;

    @Getter
    @Setter
    static class LineItem {
      private String installAt;
      private String installAtSite;
      private String transactionType;
      private String servicePid;
      private String serviceName;
      private String mandatoryService;
      private String serviceStartDate;
      private String serviceEndDate;
      private String serviceStartDateModifiable;
      private String serviceEndDateModifiable;
      private String minServiceStartDate;
      private String maxServiceEndDate;
      private String linkedToPrevious;
      private String chargeType;
      private String pricingGroup;
      private String annualListPrice;
      private String unitListPrice;
      private String priceReferenceDate;
      private String offeringGroupOrder;
      private String sowPrintGroup;
      private String platform;
      private LineItemHardware hardware;
      private List<SubLineItem> subLineItems;
      private DisplayOutput displayOutput;
      private List<Message> messages;

      public LineItem() {
        this.hardware = new LineItemHardware();
        this.subLineItems = new ArrayList<>();
        this.displayOutput = new DisplayOutput();
        this.messages = new ArrayList<>();
      }
    }

    @NoArgsConstructor
    @Getter
    @Setter
    static class Message {
      private String messageLevel;
      private String messageText;
    }

    @NoArgsConstructor
    @Getter
    @Setter
    static class LineItemHardware {
      private String productId;
      private String description;
      private String serial;
      private String installDate;
      private String warrantyEndDate;
      private String lastUpdate;
    }

    @NoArgsConstructor
    @Getter
    @Setter
    static class DisplayOutput {
      private String lineName;
      private String referenceAssetId;
      private String serial;
      private String referenceAssetDescription;
      private String quantity;
      private String warrantyEndDate;
      private String serviceLevel;
    }

    @Getter
    @Setter
    static class SubLineItem {
      private String subLineItemType;
      private String installAt;
      private String installAtSite;
      private String transactionType;
      private String feature;
      private String quantity;
      private String serviceStartDate;
      private String serviceEndDate;
      private String annualListPrice;
      private String unitCost;
      private String exhibitDiscount;
      private DisplayOutput displayOutput;
      private SubLineItemSoftware software;

      public SubLineItem() {
        this.displayOutput = new DisplayOutput();
        this.software = new SubLineItemSoftware();
      }
    }

    @NoArgsConstructor
    @Getter
    @Setter
    static class SubLineItemSoftware {
      private String productId;
      private String description;
      private String serial;
      private String lastUpdate;
    }
  }
}
