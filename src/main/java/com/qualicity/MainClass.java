package com.qualicity;

import java.util.*;

public class MainClass {

    public static void reportResults(List<List<Integer>> multiDimensionalResultsListArray, Hashtable hashTable) throws Exception {

        // Report on what we've found
        Enumeration hashTableKeys = hashTable.keys();
        int highestKeyFound = 0;

        while (hashTableKeys.hasMoreElements()) {

            int key = (int) hashTableKeys.nextElement();

            if (key > highestKeyFound)
                highestKeyFound = key;

            int actualSizeOfPond = (int) hashTable.get(key);

            if (key > multiDimensionalResultsListArray.size())
                 throw new PondFoundNotExpectedException("**** Unexpected pond number " + key + ": A pond of size " + actualSizeOfPond + " has been found but this pond has not been specified. Test FAIL ****");

            int expectedNumberOfPonds = multiDimensionalResultsListArray.get(key - 1).get(0);
            if (actualSizeOfPond < expectedNumberOfPonds)
                throw new PondTooSmallException("***** Pond number " + key + ": Pond found was size " + actualSizeOfPond + " but expected size " + expectedNumberOfPonds + ". Test FAIL ***** ");
            if (actualSizeOfPond > expectedNumberOfPonds)
                throw new PondTooLargeException("***** Pond number " + key + ": Pond found was size " + actualSizeOfPond + " but expected size " + expectedNumberOfPonds + ". Test FAIL ***** ");
        }

        int numberOfPondsExpected = multiDimensionalResultsListArray.size();

        if (numberOfPondsExpected != highestKeyFound)
            reportIssueWithNoPondsFoundForSpecificPond(multiDimensionalResultsListArray, highestKeyFound, numberOfPondsExpected);
    }

    private static void reportIssueWithNoPondsFoundForSpecificPond(List<List<Integer>> multiDimensionalResultsListArray, int highestKeyFound, int numberOfPondsExpected) throws Exception {

        for (int count = highestKeyFound + 1; count < numberOfPondsExpected + 1; count++) {

            int numberOfPondsExpectedForThisPondNumber = multiDimensionalResultsListArray.get(count - 1).get(0);

            if (numberOfPondsExpectedForThisPondNumber == 0)
                continue;

              throw new PondMissingException("***** Pond number " + count + " was not found: Was expecting a pond of size " + numberOfPondsExpectedForThisPondNumber + ". Test FAIL ***** ");
        }
    }

    public static Hashtable mainCodeLogic(List<List<Integer>> multiDimensionalArray) {

        // Set up the array which we'll use to store information about the 'ponds' found
        int numberOfRows = multiDimensionalArray.size();
        int rowWidthInCells = multiDimensionalArray.get(0).size();
        List<List<Integer>> multiDimensionalIntegerArray = setupIntegerList(numberOfRows, rowWidthInCells);

        int pondNumber = 1;
        boolean increasePondNumber = true;
        boolean firstPondFound = false;

        for (int rowCount = 0; rowCount < numberOfRows; rowCount++) {

            for (int cellCount = 0; cellCount < rowWidthInCells; cellCount++) {

                int lowestPondNumberFound = 0;
                boolean checkAllCellsForLowerPondNumber = false;

                // Move onto the next cell if it isn't zero
                if (!isCellZero(rowCount, cellCount, multiDimensionalArray)) {
                    if (increasePondNumber && firstPondFound) {
                        pondNumber++;
                        increasePondNumber = false;
                    }
                    continue;
                }

                // Are we on the first row
                if (rowCount == 0) {
                    multiDimensionalIntegerArray.get(rowCount).set(cellCount, pondNumber);
                    if (cellCount == (rowWidthInCells - 1)) {
                        pondNumber++;
                        increasePondNumber = false;
                    } else
                        increasePondNumber = true;

                    firstPondFound = true;
                    continue;
                }

                // Code below only reached when rowcount > 0
                // Firstly, check if the cell above is a known pond
                if (replacePondOccurencesIfRequired_CellAbove(multiDimensionalIntegerArray, pondNumber, rowCount, cellCount))
                    continue;

                // Check if the cell diagonally above and to the right is a known pond
                // First of all check we're not dealing with the last cell
                int pondNumberToSetToLowerValueIfRequired = 0;
                if (cellCount != (rowWidthInCells - 1)) {
                    int pondNumberDirectlyAboveAndToTheRight = multiDimensionalIntegerArray.get(rowCount - 1).get(cellCount + 1);
                    if (pondNumberDirectlyAboveAndToTheRight > 0) {

                        // We've joined with a previously existing pond with a smaller ID number
                        // Swap out all cells with the current pondNumber with the smaller pond number
                        if (pondNumberDirectlyAboveAndToTheRight < pondNumber) {
                            multiDimensionalIntegerArray.get(rowCount).set(cellCount, pondNumber);
                            replaceAllPondNumberOccurrences(pondNumber, pondNumberDirectlyAboveAndToTheRight, multiDimensionalIntegerArray);
                            lowestPondNumberFound = pondNumberDirectlyAboveAndToTheRight;
                            checkAllCellsForLowerPondNumber = true;
                            pondNumberToSetToLowerValueIfRequired = pondNumberDirectlyAboveAndToTheRight;
                        }

                        // We've joined with a previously existing pond with a higher ID number
                        // Swap out all cells with the higher pondNumber with the smaller pond number
                        else {
                            replaceAllPondNumberOccurrences(pondNumberDirectlyAboveAndToTheRight, pondNumber, multiDimensionalIntegerArray);
                        }
                        if (!checkAllCellsForLowerPondNumber)
                            continue;
                    }
                }

                // Cellcount > 1
                if (cellCount > 0) {
                    // Check to see if the cell to the immediate left is a pond
                    int pondNumberDirectlyToTheleft = multiDimensionalIntegerArray.get(rowCount).get(cellCount - 1);
                    if (pondNumberDirectlyToTheleft > 0) {
                        multiDimensionalIntegerArray.get(rowCount).set(cellCount, pondNumberDirectlyToTheleft);
                        if (checkAllCellsForLowerPondNumber) {
                            replaceAllPondNumberOccurrences(pondNumberToSetToLowerValueIfRequired, pondNumberDirectlyToTheleft, multiDimensionalIntegerArray);
                        }
                        firstPondFound = true;

                        // We're at the end of the row, therefore we need to increase PondNumber.
                        if (cellCount == rowWidthInCells - 1) {
                            pondNumber++;
                        }
                        continue;
                    }

                    // Check to see if the cell diagonally to the left of this cell is a pond
                    int pondNumberDiagonallyUpAndToTheleft = multiDimensionalIntegerArray.get(rowCount - 1).get(cellCount - 1);
                    if (replacePondNumberOccurrencesAsRequired_LeftDiagonal(multiDimensionalIntegerArray, pondNumber, rowCount, cellCount, lowestPondNumberFound, checkAllCellsForLowerPondNumber, pondNumberDiagonallyUpAndToTheleft))
                        continue;
                }

                // Continue when checkAllCellsForLowerPondNumber is true as the pondNumber has already been set to the cell by this point
                if (checkAllCellsForLowerPondNumber)
                    continue;

                // Aha we have a new pond
                multiDimensionalIntegerArray.get(rowCount).set(cellCount, pondNumber);
                increasePondNumber = true;
                firstPondFound = true;
                if (cellCount == rowWidthInCells - 1) {
                    pondNumber++;
                }

            }
        }

        // Parse the array which contains information about different ponds
        // Populate a hashtable with the quantity of each pond number. E.g. there might be 3 cells that correspond to pond 1, 2 for pond 2 etc
        Hashtable hashTable = parseArrayToCreateHashtable(numberOfRows, rowWidthInCells, multiDimensionalIntegerArray);
        return hashTable;
    }

    private static boolean replacePondOccurencesIfRequired_CellAbove(List<List<Integer>> multiDimensionalIntegerArray, int pondNumber, int rowCount, int cellCount) {
        int pondNumberDirectlyAbove = multiDimensionalIntegerArray.get(rowCount - 1).get(cellCount);
        if (pondNumberDirectlyAbove > 0) {
            if (pondNumberDirectlyAbove < pondNumber) {
                multiDimensionalIntegerArray.get(rowCount).set(cellCount, pondNumber);
                replaceAllPondNumberOccurrences(pondNumber, pondNumberDirectlyAbove, multiDimensionalIntegerArray);
            }
            return true;
        }
        return false;
    }

    private static boolean replacePondNumberOccurrencesAsRequired_LeftDiagonal(List<List<Integer>> multiDimensionalIntegerArray, int pondNumber, int rowCount, int cellCount, int lowestPondNumberFound, boolean checkAllCellsForLowerPondNumber, int pondNumberDiagonallyUpAndToTheLeft) {

        if (pondNumberDiagonallyUpAndToTheLeft > 0) {
            if (checkAllCellsForLowerPondNumber && (lowestPondNumberFound > pondNumberDiagonallyUpAndToTheLeft)) {
                multiDimensionalIntegerArray.get(rowCount).set(cellCount, pondNumberDiagonallyUpAndToTheLeft);
                replaceAllPondNumberOccurrences(lowestPondNumberFound, pondNumberDiagonallyUpAndToTheLeft, multiDimensionalIntegerArray);
            }
            if (pondNumberDiagonallyUpAndToTheLeft < pondNumber) {
                multiDimensionalIntegerArray.get(rowCount).set(cellCount, pondNumberDiagonallyUpAndToTheLeft);
                replaceAllPondNumberOccurrences(pondNumber, pondNumberDiagonallyUpAndToTheLeft, multiDimensionalIntegerArray);
            } else {
                replaceAllPondNumberOccurrences(pondNumberDiagonallyUpAndToTheLeft, pondNumber, multiDimensionalIntegerArray);
            }
            return true;
        }
        return false;
    }

    private static Hashtable parseArrayToCreateHashtable(int numberOfRows, int rowWidthInCells, List<List<Integer>> multiDimensionalIntegerArray) {
        Hashtable hashTable = new Hashtable<>();
        for (int rowCount = 0; rowCount < numberOfRows; rowCount++) {

            for (int cellCount = 0; cellCount < rowWidthInCells; cellCount++) {
                int cellValue = multiDimensionalIntegerArray.get(rowCount).get(cellCount);
                if (cellValue == 0)
                    continue;

                if (!hashTable.containsKey(cellValue)) {
                    hashTable.put(cellValue, 1);
                } else {
                    int quantityOfPonds = (int) hashTable.get(cellValue);
                    quantityOfPonds++;
                    hashTable.put(cellValue, quantityOfPonds);
                }
            }
        }
        return hashTable;
    }

    private static void replaceAllPondNumberOccurrences(int pondNumberToBeReplaced, int newPondNumber, List<List<Integer>> multiDimensionalIntegerArray) {

        for (List<Integer> integers : multiDimensionalIntegerArray) {
            for (int cellCount = 0; cellCount < multiDimensionalIntegerArray.get(0).size(); cellCount++) {
                int cellValue = integers.get(cellCount);
                if (cellValue == pondNumberToBeReplaced) {
                    integers.set(cellCount, newPondNumber);
                }
            }
        }
    }

    private static List<List<Integer>> setupIntegerList(int numberOfRows, int arrayWidth) {
        List<List<Integer>> multiDimensionalIntegerArray = new ArrayList<>();

        for (int count = 0; count < numberOfRows; count++) {
            List<Integer> integerRow = new ArrayList<>();
            for (int count2 = 0; count2 < arrayWidth; count2++) {
                integerRow.add(0);
            }
            multiDimensionalIntegerArray.add(integerRow);
        }

        return multiDimensionalIntegerArray;
    }

    private static boolean isCellZero(int rowNumber, int cellNumber, List<List<Integer>> multiDimensionalArray) {
        return multiDimensionalArray.get(rowNumber).get(cellNumber) == 0;
    }
}