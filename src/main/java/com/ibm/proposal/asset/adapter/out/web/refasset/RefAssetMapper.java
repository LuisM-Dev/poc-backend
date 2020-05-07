package com.ibm.proposal.asset.adapter.out.web.refasset;

import com.google.gson.Gson;
import com.ibm.proposal.asset.adapter.out.web.utils.Request;
import com.ibm.proposal.asset.domain.Asset;
import com.ibm.proposal.asset.domain.RefAsset;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class RefAssetMapper {

  String mapToRequestBody(Asset asset) {
    RequestModel.FilterModel filterModel = new RequestModel.FilterModel();
    filterModel.assetSerialNumber = asset.getAssetSerialNumber();
    filterModel.countryCode = asset.getCountryCode();
    filterModel.installedCustomerNumber = asset.getInstalledCustomerNumber();
    filterModel.model = asset.getModel();
    filterModel.type = asset.getType();
    filterModel.recordType = asset.getRecordType();
    filterModel.soldTo = asset.getSoldTo();
    List<RequestModel.FilterModel> filters = new ArrayList<>();
    filters.add(filterModel);
    RequestModel requestModel = new RequestModel(filters, "norm");
    return new Gson().toJson(requestModel);
  }

  List<RefAsset> mapToRefAsset(String responseBody) {
    ResponseModel responseModel = new Gson().fromJson(responseBody, ResponseModel.class);
    List<RefAsset> refAssets = new ArrayList<>();
    responseModel.hardware.forEach(hardwareAsset -> {
      RefAsset refAsset = new RefAsset();
      refAsset.setInstallDate(parseDate(hardwareAsset.installDate));
      refAsset.setAssetSerialNumber(hardwareAsset.assetSerialNumber);
      refAsset.setInstalledCustomerNumber(
              hardwareAsset.installedCustomerNumber.trim());
      refAsset.setModel(hardwareAsset.model);
      refAsset.setSitePartyId(hardwareAsset.sitePartyId);
      refAsset.setWarrantyEndDate(parseDate(hardwareAsset.warrantyEndDate));
      refAsset.setType(hardwareAsset.type);
      refAssets.add(refAsset);
    });
    return refAssets;
  }

  private String parseDate(String date) {
    if(date.contains("Z")) {
      return LocalDate.parse(date.split("Z")[0]).toString();
    }
    return date;
  }

  @NoArgsConstructor
  @AllArgsConstructor
  static class RequestModel {
    private List<FilterModel> searchFilter;
    private String flag;

    @NoArgsConstructor
    @AllArgsConstructor
    static class FilterModel {
      private String installedCustomerNumber;
      private String type;
      private String model;
      private String assetSerialNumber;
      private String soldTo;
      private String recordType;
      private String countryCode;
    }
  }

  @NoArgsConstructor
  @AllArgsConstructor
  static class ResponseModel {
    private List<HardwareAsset> hardware;

    @NoArgsConstructor
    @AllArgsConstructor
    static class HardwareAsset {
      private String installedCustomerNumber;
      private String type;
      private String model;
      private String assetSerialNumber;
      private String installDate;
      private String warrantyEndDate;
      private String sitePartyId;
    }
  }
}
