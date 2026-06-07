Feature: To check the end to end flow

Scenario: To verify the checkout journey.

Given The user is on the Greenkart site
When User adds vegetables in the cart
When Clicks on the checkout button 
And checkout is completed
Then User gets a successful message