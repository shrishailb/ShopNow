Feature: ShowNow functionality

  ##Note: - he requirement was automate flipkart using username and password, but that requirement doesn't exists in flipkart production
  ##        website and we have to add OTP manually.
  @HTL123
  Scenario Outline: Test Happy pathflow for shopNow website:
    Given User opens the Flipkart website "<URL>" "<Browser>"
    When User search and add to cart
    Then proceed to checkout
    And verify the user authentication "<Username>"
    Then add the shipping information
    Then review the order
    And proceed to payment method


    Examples:
    |URL                        |Browser   |Username|
    | https://www.flipkart.com  |  Chrome  |  8660484759      |
   # | https://www.flipkart.com  |  Firefox  |  8660484759      |
   # | https://www.flipkart.com  |  Edge  |  8660484759      |
