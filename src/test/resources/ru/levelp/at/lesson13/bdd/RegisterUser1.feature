Feature: Registration user

    Scenario Outline: Negative user registration
        Given I am on the Login-Registration page
        When I register user with list data:
            | username   | email   | password   |
            | <username> | <email> | <password> |
        Then error message should be equal to 'register_not_correct_field (email)' in the Registration section on the Registration page

        Examples:
            | username | email | password |
            | u1       | e1    | ppppp    |
            | u2       | e2    | ppppp    |
            | u3       | e3    | ppppp    |
            | u4       | e4    | ppppp    |

    Scenario:Negative user registration map
        Given I am on the Login-Registration page
        When I register user with map data:
            | username | usdufhsdf  |
            | email    | 8328917978 |
            | password | popoeoe    |
        Then error message should be equal to 'register_not_correct_field (email)' in the Registration section on the Registration page
        # так писать нельзя
#        When I register user with map data:
#            | username | usdufhsdf  |
#            | email    | 8328917978 |
#            | password | popoeoe    |
#        Then error message should be equal to 'register_not_correct_field (email)' in the Registration section on the Registration page
#        When I register user with map data:
#            | username | usdufhsdf  |
#            | email    | 8328917978 |
#            | password | popoeoe    |
#        Then error message should be equal to 'register_not_correct_field (email)' in the Registration section on the Registration page


    Scenario:Negative user registration datatable
        Given I am on the Login-Registration page
        When I register user with datatable data:
            | username | 1252345234 |
            | email    | asdfbsdfb  |
            | password | popoeoe    |
        Then error message should be equal to 'register_not_correct_field (email)' in the Registration section on the Registration page
