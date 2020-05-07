package com.ibm.proposal.header.domain;

public enum HeaderType {
  DISTRIBUTOR("TSS Distributor Transactional"),
  DIRECT("TSS Direct Transactional");

  private final String type;

  private HeaderType(String type) {
      this.type = type;
  }
   public String getType() {
      return type;
   }
}
