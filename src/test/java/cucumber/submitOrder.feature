Feature: Purchase the order from E-commerce site

  Background:
    Given I landed on Ecommerce page

  Scenario Outline: Positive Test of Submitting the order
    Given Logged in with username <name> and password <password>
    When I add the <productName> from cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on the confirmation page

  Examples:
   |name					| password  | productName	|
   |alamelu.g@gmail.com     | Alamelu24 | ZARA COAT 3   |