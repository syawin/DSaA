Feature: Team combinations
    Enumerate all possible combinations into a group of size K from a set of N unique elements

    Scenario:
        Given a selection set "A,B,C,D,E"
        And a team size 3
        When I execute the team combo recursive algorithm
        Then the result returned is "ABC,ABD,ABE,ACD,ACE,ADE,BCD,BCE,BDE,CDE"
