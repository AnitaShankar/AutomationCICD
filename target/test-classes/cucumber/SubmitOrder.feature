
@tag
Feature: Purchase the order from E-Commerce website 
  I want to use this template for my feature file
  
  Background:
  Given I landed on Ecommerce page


  @tag2
  Scenario Outline: Positive test of Submitting the order
    Given Logged in with username <name> and password <password>
    When I add the productname <productName> to cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displyed on confirmation Page

    Examples: 
      | name  												| password 	 | productName |
      | anitamareppanavar18@gmail.com | Anita@1999 | ZARA COAT 3 |
     
