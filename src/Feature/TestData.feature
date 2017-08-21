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
      |1|3|
      |2|0|
    Then the correct numbers of each pond numbers are found


  Scenario:Test Case 4

    Given I have a test matrix as follows
      |0|1|
      |0|1|
    And expected pond results as follows
      |1|2|
      |2|0|
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
      |2|0|
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


  #Should result in a failure when uncommented because expected numbers for ponds 4 and 5 havent been specified
  #Scenario:Test Case 13
  #
  #  Given I have a test matrix as follows
  #    |0|1|0|1|0|
  #    |1|1|1|1|1|
  #    |1|0|1|0|0|
  #  And expected pond results as follows
  #    |1|1|
  #    |2|1|
  #    |3|1|
  #  Then the correct numbers of each pond numbers are found


  Scenario:Test Case 14

    Given I have a test matrix as follows
      |0|1|1|1|
      |1|1|1|0|
      |0|1|1|1|
    And expected pond results as follows
      |1|1|
      |2|1|
      |3|1|
    Then the correct numbers of each pond numbers are found