Feature: Creating new proposal
  User be able to create a new proposal.

  Scenario Outline: Creating new proposal
    Given User wants to create new proposal <type>
    When User submits the request
    Then User receives available <fields> with default values

    Examples:
      | type                            | fields                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            |
      | "TSS Distributor Transactional" | "{\"id\":\"6\",\"type\":\"TSS Distributor Transactional\",\"fields\":[{\"name\":\"leadCountry\",\"value\":\"AT\",\"required\":\"true\",\"options\":[]},{\"name\":\"account\",\"value\":\"\",\"required\":\"true\",\"options\":[]},{\"name\":\"customer\",\"value\":\"\",\"required\":\"false\",\"options\":[]},{\"name\":\"billTo\",\"value\":\"\",\"required\":\"false\",\"options\":[]},{\"name\":\"startDate\",\"value\":\"2020-02-22\",\"required\":\"false\",\"options\":[]},{\"name\":\"termLengthMonths\",\"value\":\"36\",\"required\":\"true\",\"options\":[\"12\",\"36\",\"60\"]},{\"name\":\"endDate\",\"value\":\"2020-02-22\",\"required\":\"false\",\"options\":[]},{\"name\":\"billingFrequency\",\"value\":\"Prepay\",\"required\":\"false\",\"options\":[\"Quarterly\",\"Annually\",\"Prepay\"]},{\"name\":\"validUntilDate\",\"value\":\"2020-02-22\",\"required\":\"false\",\"options\":[]},{\"name\":\"billingPreference\",\"value\":\"Exact Cycle Time\",\"required\":\"false\",\"options\":[]},{\"name\":\"pricingDate\",\"value\":\"2020-02-22\",\"required\":\"false\",\"options\":[]},{\"name\":\"description\",\"value\":\"\",\"required\":\"false\",\"options\":[]},{\"name\":\"fulfillmentChannel\",\"value\":\"Distributor\",\"required\":\"false\",\"options\":[]},{\"name\":\"customerReference\",\"value\":\"\",\"required\":\"false\",\"options\":[]},{\"name\":\"currency\",\"value\":\"EUR - Euro\",\"required\":\"true\",\"options\":[\"EUR - Euro\",\"$ - Dollar\"]},{\"name\":\"tsaAgreement\",\"value\":\"\",\"required\":\"false\",\"options\":[]},{\"name\":\"owner\",\"value\":\"\",\"required\":\"false\",\"options\":[]},{\"name\":\"agreement\",\"value\":\"\",\"required\":\"false\",\"options\":[]},{\"name\":\"billingHistory\",\"value\":\"Billing History was provided within 18 months\",\"required\":\"false\",\"options\":[\"Billing History was provided within 18 months\",\"No Billing History was provided within 18 months\"]},{\"name\":\"creditCheckAmount\",\"value\":\"\",\"required\":\"false\",\"options\":[]},{\"name\":\"creditCheckIndicator\",\"value\":\"false\",\"required\":\"false\",\"options\":[]},{\"name\":\"createdBy\",\"value\":\"\",\"required\":\"false\",\"options\":[]},{\"name\":\"currentConfigurationStatus\",\"value\":\"\",\"required\":\"false\",\"options\":[]},{\"name\":\"lastModifiedBy\",\"value\":\"\",\"required\":\"false\",\"options\":[]},{\"name\":\"approvalStage\",\"value\":\"\",\"required\":\"false\",\"options\":[]},{\"name\":\"distributorAccount\",\"value\":\"\",\"required\":\"false\",\"options\":[]},{\"name\":\"tier2Account\",\"value\":\"\",\"required\":\"false\",\"options\":[]},{\"name\":\"bpa2ndTierCheck\",\"value\":\"false\",\"required\":\"false\",\"options\":[]},{\"name\":\"bpa2ndTierExpireDate\",\"value\":\"2020-02-22\",\"required\":\"false\",\"options\":[]},{\"name\":\"retroactiveCheck\",\"value\":\"false\",\"required\":\"false\",\"options\":[]},{\"name\":\"creditCheckExpirationDate\",\"value\":\"2020-02-22\",\"required\":\"false\",\"options\":[]},{\"name\":\"retroDocument\",\"value\":\"\",\"required\":\"false\",\"options\":[]},{\"name\":\"creditCheckDocument\",\"value\":\"\",\"required\":\"false\",\"options\":[]}]}" |
      | "TSS Direct Transactional"      | "{\"id\":\"5\",\"type\":\"TSS Direct Transactional\",\"fields\":[{\"name\":\"leadCountry\",\"value\":\"AT\",\"required\":\"true\",\"options\":[]},{\"name\":\"account\",\"value\":\"\",\"required\":\"true\",\"options\":[]},{\"name\":\"customer\",\"value\":\"\",\"required\":\"false\",\"options\":[]},{\"name\":\"billTo\",\"value\":\"\",\"required\":\"false\",\"options\":[]},{\"name\":\"startDate\",\"value\":\"2020-02-22\",\"required\":\"false\",\"options\":[]},{\"name\":\"termLengthMonths\",\"value\":\"36\",\"required\":\"true\",\"options\":[\"12\",\"36\",\"60\"]},{\"name\":\"endDate\",\"value\":\"2020-02-22\",\"required\":\"false\",\"options\":[]},{\"name\":\"billingFrequency\",\"value\":\"Prepay\",\"required\":\"false\",\"options\":[\"Quarterly\",\"Annually\",\"Prepay\"]},{\"name\":\"validUntilDate\",\"value\":\"2020-02-22\",\"required\":\"false\",\"options\":[]},{\"name\":\"billingPreference\",\"value\":\"Exact Cycle Time\",\"required\":\"false\",\"options\":[]},{\"name\":\"pricingDate\",\"value\":\"2020-02-22\",\"required\":\"false\",\"options\":[]},{\"name\":\"description\",\"value\":\"\",\"required\":\"false\",\"options\":[]},{\"name\":\"fulfillmentChannel\",\"value\":\"Direct\",\"required\":\"false\",\"options\":[]},{\"name\":\"customerReference\",\"value\":\"\",\"required\":\"false\",\"options\":[]},{\"name\":\"currency\",\"value\":\"EUR - Euro\",\"required\":\"true\",\"options\":[\"EUR - Euro\",\"$ - Dollar\"]},{\"name\":\"tsaAgreement\",\"value\":\"\",\"required\":\"false\",\"options\":[]},{\"name\":\"owner\",\"value\":\"\",\"required\":\"false\",\"options\":[]},{\"name\":\"agreement\",\"value\":\"\",\"required\":\"false\",\"options\":[]},{\"name\":\"billingHistory\",\"value\":\"Billing History was provided within 18 months\",\"required\":\"false\",\"options\":[\"Billing History was provided within 18 months\",\"No Billing History was provided within 18 months\"]},{\"name\":\"creditCheckAmount\",\"value\":\"\",\"required\":\"false\",\"options\":[]},{\"name\":\"creditCheckIndicator\",\"value\":\"false\",\"required\":\"false\",\"options\":[]},{\"name\":\"createdBy\",\"value\":\"\",\"required\":\"false\",\"options\":[]},{\"name\":\"currentConfigurationStatus\",\"value\":\"\",\"required\":\"false\",\"options\":[]},{\"name\":\"lastModifiedBy\",\"value\":\"\",\"required\":\"false\",\"options\":[]},{\"name\":\"approvalStage\",\"value\":\"\",\"required\":\"false\",\"options\":[]},{\"name\":\"customerPODriven\",\"value\":\"true\",\"required\":\"false\",\"options\":[]},{\"name\":\"creditCheckAmount\",\"value\":\"\",\"required\":\"false\",\"options\":[]},{\"name\":\"eroExempted\",\"value\":\"\",\"required\":\"false\",\"options\":[]},{\"name\":\"eroApproval\",\"value\":\"\",\"required\":\"false\",\"options\":[]},{\"name\":\"creditCheckExpirationDate\",\"value\":\"2020-02-22\",\"required\":\"false\",\"options\":[]},{\"name\":\"eroDocumentLink\",\"value\":\"\",\"required\":\"false\",\"options\":[]},{\"name\":\"retroactiveCheck\",\"value\":\"false\",\"required\":\"false\",\"options\":[]},{\"name\":\"retroDocument\",\"value\":\"\",\"required\":\"false\",\"options\":[]}]}"                                    |

  Scenario: Creating new proposal with incorrect type
    Given User wants to create new proposal "Some Type"
    When User submits the request
    Then User receives an error message "Proposal Type is invalid"