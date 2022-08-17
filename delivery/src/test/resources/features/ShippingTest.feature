Feature: Shipping
    Scenario Outline: 
        Given OrderPlaced ( ) is published and  ( ) is present
        When start delivery
        Then  ( ) should publish

        Examples:
            |
            |      |   |    |


