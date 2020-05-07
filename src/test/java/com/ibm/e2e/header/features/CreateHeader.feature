Feature: Create Proposal Header
  User to be able to create header for new proposal.

  Scenario: Creating new Header
    Given New Proposal with id "1" and type <type>
    When User saves header <fields>
    Then Header is saved successfully

    | type | fields |
    | TSS Distributor Transactional | |
    | TSS Direct Transactional | |