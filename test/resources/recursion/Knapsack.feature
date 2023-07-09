Feature: Knapsack problem
    Pick elements from a provided list that add up to a given target.

#    This fucking scenario took way too long to implement
    Scenario Outline: Knapsack problem
        Given a list containing <numbers>
        And the target <target>
        When I execute the recursive algorithm
        Then I should have <expected> as my result

        Examples:
            | numbers                         | target | expected         |
            | "1,2,3"                         | 5      | "2,3"            |
            | "12, 7, 11, 8, 9, 5"            | 20     | "8,12"           |
            | "23,31,29,44,53,38,63,85,89,82" | 165    | "23,31,29,44,38" |

    Scenario Outline: Knapsack problem - error path
        Given a list containing <numbers>
        And the target <target>
        When I execute the recursive algorithm
        Then I should have no result

        Examples:
            | numbers | target |
            | "3"     | 5      |
            | "9,10"  | 1      |
            | "3,4,5" | 10     |
