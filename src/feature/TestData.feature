Feature: Testing the pond program algorithms

  Scenario: Test Case 1

    Given I have a test matrix as follows
      |0|1|1|1|
      |0|1|0|0|
    And expected pond results as follows
      |1|2|
      |2|2|
    Then the correct numbers of each pond numbers are found

  Scenario: Test Case 2

    Given I have a test matrix as follows
      |0|1|1|1|
      |0|1|0|0|
      |0|1|1|1|
    And expected pond results as follows
      |1|3|
      |2|2|
      |2|0|
    Then the correct numbers of each pond numbers are found

  Scenario: Test Case 3

    Given I have a test matrix as follows
      |0|1|1|1|
      |0|1|0|0|
    And expected pond results as follows
      |1|2|
      |2|2|
    Then the correct numbers of each pond numbers are found

  Scenario:Test Case 4

    Given I have a test matrix as follows
      |0|1|
      |0|1|
    And expected pond results as follows
      |1|2|
    Then the correct numbers of each pond numbers are found

  Scenario:Test Case 5

    Given I have a test matrix as follows
      |0|1|0|0|
      |1|0|1|1|
    And expected pond results as follows
      |1|4|
      |2|0|
    Then the correct numbers of each pond numbers are found

  Scenario:Test Case 6

    Given I have a test matrix as follows
      |1|1|0|0|
      |1|0|1|1|
    And expected pond results as follows
      |1|3|
      |2|0|
    Then the correct numbers of each pond numbers are found

  Scenario:Test Case 7

    Given I have a test matrix as follows
      |0|1|0|
      |1|0|1|
      |0|1|0|
    And expected pond results as follows
      |1|5|
    Then the correct numbers of each pond numbers are found

  Scenario:Test Case 8

    Given I have a test matrix as follows
      |0|
      |0|
    And expected pond results as follows
      |1|2|
    Then the correct numbers of each pond numbers are found

  Scenario:Test Case 9

    Given I have a test matrix as follows
      |0|1|0|1|
      |0|1|1|0|
      |0|1|1|1|
    And expected pond results as follows
      |1|3|
      |2|2|
    Then the correct numbers of each pond numbers are found

  Scenario:Test Case 10

    Given I have a test matrix as follows
      |0|1|0|1|1|0|
      |0|1|1|0|1|0|
      |1|1|1|1|1|1|
    And expected pond results as follows
      |1|2|
      |2|2|
      |3|2|
    Then the correct numbers of each pond numbers are found

  Scenario:Test Case 11

    Given I have a test matrix as follows
      |0|1|0|1|
      |0|1|1|0|
    And expected pond results as follows
      |1|2|
      |2|2|
    Then the correct numbers of each pond numbers are found

  Scenario:Test Case 12

    Given I have a test matrix as follows
      |0|1|1|0|
      |0|0|0|0|
      |0|1|1|0|
    And expected pond results as follows
      |1|8|
    Then the correct numbers of each pond numbers are found

  Scenario:Test Case 13

    Given I have a test matrix as follows
      |0|1|1|1|
      |1|1|1|0|
      |0|1|1|1|
    And expected pond results as follows
      |1|1|
      |2|1|
      |3|1|
    Then the correct numbers of each pond numbers are found

  Scenario:Test Case 14

    Given I have a test matrix as follows
      |0|0|1|1|0|0|1|
    And expected pond results as follows
      |1|2|
      |2|2|
    Then the correct numbers of each pond numbers are found

  Scenario:Test Case 15

    Given I have a test matrix as follows
      |1|
    And expected pond results as follows
      |1|0|
    Then the correct numbers of each pond numbers are found

  Scenario:Test Case 16

    Given I have a test matrix as follows
      |1|0|
    And expected pond results as follows
      |1|1|
    Then the correct numbers of each pond numbers are found

  Scenario:Test Case 17

    Given I have a test matrix as follows
      |1|0|0|
    And expected pond results as follows
      |1|2|
    Then the correct numbers of each pond numbers are found

  Scenario:Test Case 18

    Given I have a test matrix as follows
      |0|1|
    And expected pond results as follows
      |1|1|

    Then the correct numbers of each pond numbers are found

  Scenario:Test Case 19

    Given I have a test matrix as follows
      |0|0|1|
    And expected pond results as follows
      |1|2|
    Then the correct numbers of each pond numbers are found

  Scenario:Test Case 20

    Given I have a test matrix as follows
      |0|1|1|
    And expected pond results as follows
      |1|1|
    Then the correct numbers of each pond numbers are found

  Scenario:Test Case 21

    Given I have a test matrix as follows
      |1|0|1|1|
    And expected pond results as follows
      |1|1|
    Then the correct numbers of each pond numbers are found

  Scenario:Test Case 22

    Given I have a test matrix as follows
      |0|1|0|1|
    And expected pond results as follows
      |1|1|
      |2|1|
    Then the correct numbers of each pond numbers are found

  Scenario:Test Case 23

    Given I have a test matrix as follows
      |1|1|1|1|0|0|1|0|
    And expected pond results as follows
      |1|2|
      |2|1|
    Then the correct numbers of each pond numbers are found

  Scenario:Test Case 24

    Given I have a test matrix as follows
      |0|0|
      |0|0|
    And expected pond results as follows
      |1|4|
    Then the correct numbers of each pond numbers are found

  Scenario:Test Case 25

    Given I have a test matrix as follows
      |0|0|0|
      |0|1|0|
      |0|0|0|
    And expected pond results as follows
      |1|8|
    Then the correct numbers of each pond numbers are found

  Scenario:Test Case 26

    Given I have a test matrix as follows
      |1|0|
      |0|1|
    And expected pond results as follows
      |1|2|
    Then the correct numbers of each pond numbers are found

  Scenario: Test Case 27 - the wiggly worm

    Given I have a test matrix as follows
      |0|0|0|0|0|0|0|0|0|0|
      |0|1|1|1|1|1|1|1|1|0|
      |0|1|0|0|0|0|0|0|1|0|
      |0|1|0|1|1|1|1|0|1|0|
      |0|1|0|1|0|0|1|0|1|0|
      |0|1|0|1|1|0|1|0|1|0|
      |0|1|0|1|1|0|1|0|1|0|
      |0|1|0|0|0|0|1|0|1|0|
      |0|1|1|1|1|1|1|0|1|0|
      |0|0|0|0|0|0|0|0|1|0|
    And expected pond results as follows
      |1|59|
    Then the correct numbers of each pond numbers are found

  Scenario: Test Case 28 - the hierarchy

    Given I have a test matrix as follows
      |1|1|1|1|0|1|1|1|1|1|
      |1|1|1|1|0|1|1|1|1|1|
      |1|1|1|1|0|1|1|1|1|1|
      |1|1|0|0|1|0|0|1|1|1|
      |1|1|0|1|1|1|0|1|1|1|
      |0|0|0|0|1|0|0|0|0|1|
      |0|1|1|0|1|0|1|1|0|1|
      |0|1|1|0|1|0|1|1|0|1|
      |1|1|1|1|1|1|1|1|1|1|
      |1|1|1|1|1|1|1|1|1|1|
    And expected pond results as follows
      |1|25|
    Then the correct numbers of each pond numbers are found

  Scenario:Test Case 29

    Given I have a test matrix as follows
      |0|
    And expected pond results as follows
      |1|1|
    Then the correct numbers of each pond numbers are found

  ####################################################################################
  # Error scenarios - check tests fail appropriately
  ####################################################################################

  #Error check - second pond will be found but it is not specified in the results table, therefore an exception should result
  Scenario:Test Case Error Detection Check 1

    Given I have a test matrix as follows
      |0|1|0|
      |0|1|0|
      |0|1|0|
    And expected pond results as follows
      |1|3|
    Then an unspecified pond will be found

  #Error check - third pond is specified in the results table but will not be found, therefore an exception should result
  Scenario:Test Case Error Detection Check 2

    Given I have a test matrix as follows
      |0|1|0|
      |0|1|0|
      |0|1|0|
    And expected pond results as follows
      |1|3|
      |2|3|
      |3|3|
    Then a pond is seen to be missing

  #Error check - second pond is specified but the actual size will be found to be too big, therefore an exception should result
  Scenario:Test Case Error Detection Check 3

    Given I have a test matrix as follows
      |0|1|0|
      |0|1|0|
      |0|1|0|
    And expected pond results as follows
      |1|3|
      |2|2|
    Then a pond is found to be too large

  #Error check - second pond is specified but the actual size will be smaller than expected, therefore an exception should result
  Scenario:Test Case Error Detection Check 4

    Given I have a test matrix as follows
      |0|1|0|
      |0|1|0|
      |0|1|0|
    And expected pond results as follows
      |1|3|
      |2|4|
    Then a pond is found to be too small


