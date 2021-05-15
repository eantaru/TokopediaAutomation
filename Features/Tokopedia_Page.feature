
Feature: Tokopedia mobile phone page

  @tokopedia
  Scenario: Browse tokopedia mobile phone page
    Given A user browse tokopedia mobile phone page
    Then Wait until page fully open and Scroll down and list all phones 
    And Close the browser
    Then Store from volatile storage to csv
