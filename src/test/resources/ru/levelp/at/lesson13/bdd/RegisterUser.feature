Feature: Register user userbugred

    Scenario: Positive user registration
        Given I am on the Login-Registration page
        When I enter 'nickname' to 'Имя' text field in the Registration section on the Login-Registration page
        And I enter 'nickname@email.com' to 'Email' text field in the Registration section on the Login-Registration page
        And I enter 'password' to 'Password' text field in the Registration section on the Login-Registration page
        And I click to 'Зарегистрироваться' button in the Registration section on the Login-Registration page
        Then displayed username should be equal to 'nickname' on the Main page

