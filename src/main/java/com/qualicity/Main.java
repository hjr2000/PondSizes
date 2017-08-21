package com.qualicity;

import java.util.*;

public class Main {

    static boolean testsAllPassed = true;
    static int numberOfFailingTests = 0;
    static List<String> failingTests = new ArrayList<>();

    public static void main(String[] args) throws Exception {

        // Single-row test cases
        //List<Integer> row0 = Arrays.asList(1);
        //List<Integer> row0 = Arrays.asList(10);
        //List<Integer> row0 = Arrays.asList(100);
        //List<Integer> row0 = Arrays.asList(01);
        //List<Integer> row0 = Arrays.asList(001);
        //List<Integer> row0 = Arrays.asList(011);

        //List<Integer> row0 = Arrays.asList(0, 1, 0, 1);


        //List<Integer> row0 = Arrays.asList(1,1,1,1,0,0,1,0);



        reportGlobalTestPassOrFail();
    }

    private static void reportGlobalTestPassOrFail() {

        if (!testsAllPassed){
            System.out.println("There are TEST FAILURES. Failed tests:\n");

            for (int count =0 ; count <failingTests.size(); count++){
                System.out.println(failingTests.get(count));
            }
            System.out.println("\nNumber of failing tests: " + numberOfFailingTests);
        }
        else {
            System.out.println("\nAll tests passed");
        }
    }

    private static void clearData(List<List<Integer>> multiDimensionalArray, List<List<Integer>> multiDimensionalResultsListArray, Hashtable hashTable) {
        multiDimensionalArray.clear();
        multiDimensionalResultsListArray.clear();
        hashTable.clear();
    }

    public static void reportResults(List<List<Integer>> multiDimensionalResultsListArray, String testName, Hashtable hashTable) throws Exception {
//hjr2
        // Report on what we've found

        Enumeration hashTableKeys = hashTable.keys();
        boolean thisTestFailed = false;
        List<String> passedTests = new ArrayList<>();
        int passedTestNumber = 0;
        int highestKeyFound = 0;
        boolean testIDDisplayed = false;

        while(hashTableKeys.hasMoreElements()) {

            int key = (int)hashTableKeys.nextElement();

            if (key > highestKeyFound)
                highestKeyFound = key;

            int actualNumberOfPonds = (int) hashTable.get(key);
            int resultsArraySize = multiDimensionalResultsListArray.size();

            if (key > resultsArraySize){
                if(!testIDDisplayed) {
                    System.out.println("Test: " + testName);
                    testIDDisplayed = true;
                }
                System.out.println("Pond number " + key + ": " + actualNumberOfPonds + " ponds have been found but the quantity expected has not been specified. TEST FAIL");
                throw new Exception("Pond number " + key + ": " + actualNumberOfPonds + " ponds have been found but the quantity expected has not been specified. TEST FAIL");
                /*testsAllPassed = false;

                if (!thisTestFailed){
                    numberOfFailingTests++;
                    failingTests.add(testName);
                }

                thisTestFailed = true;
                continue;*/
            }

            int expectedNumberOfPonds = multiDimensionalResultsListArray.get(key - 1).get(0);
            if (actualNumberOfPonds==expectedNumberOfPonds){
                if (thisTestFailed){
                    System.out.println("Pond number " + key + ": " + actualNumberOfPonds + " ponds found. Test pass");
                    passedTestNumber++;
                }
                passedTests.add("Pond number " + key + ": " + actualNumberOfPonds + " ponds found. Test pass");
                passedTestNumber++;
            }
            else {
                thisTestFailed = reportIncorrectNumberOfPondsForSpecificPondNumber(testName, thisTestFailed, passedTests, passedTestNumber, testIDDisplayed, key, actualNumberOfPonds, expectedNumberOfPonds);
            }
        }

        int numberOfPondsExpected = multiDimensionalResultsListArray.size();

        reportIssueWithNoPondsFoundForSpecificPond(multiDimensionalResultsListArray, thisTestFailed, passedTests, passedTestNumber, highestKeyFound, testIDDisplayed, numberOfPondsExpected, testName);
    }

    private static boolean reportIncorrectNumberOfPondsForSpecificPondNumber(String testName, boolean thisTestFailed, List<String> passedTests, int passedTestNumber, boolean testIDDisplayed, int key, int actualNumberOfPonds, int expectedNumberOfPonds) {

        if (!thisTestFailed) {
            if (!testIDDisplayed) {
                System.out.println("Test: " + testName);
            }

            if (passedTestNumber > 0) {
                for (int count = 0; count < passedTests.size(); count++){
                    System.out.println(passedTests.get(count));
                }
            }
        }

        System.out.println("Pond number " + key + ": " + actualNumberOfPonds + " ponds found but expected " +  expectedNumberOfPonds + ". Test FAIL");
        testsAllPassed = false;

        if (!thisTestFailed){
            numberOfFailingTests++;
            failingTests.add(testName);
        }
        thisTestFailed = true;
        return thisTestFailed;
    }

    private static void reportIssueWithNoPondsFoundForSpecificPond(List<List<Integer>> multiDimensionalResultsListArray, boolean thisTestFailed, List<String> passedTests, int passedTestNumber, int highestKeyFound, boolean testIDDisplayed, int numberOfPondsExpected, String testName) {

        if (numberOfPondsExpected != highestKeyFound) {
            //System.out.println("numberOfPondsExpected : " + numberOfPondsExpected);

            for (int count = highestKeyFound + 1; count < numberOfPondsExpected +1; count++){
                int numberOfPondsExpectedForThisPondNumber = multiDimensionalResultsListArray.get(count - 1).get(0);

                if (numberOfPondsExpectedForThisPondNumber == 0)
                    continue;

                reportTestNameAndPassedTests(thisTestFailed, passedTests, passedTestNumber, testIDDisplayed, testName);

                System.out.println("Pond number " + count + ": 0 ponds found but expected " + numberOfPondsExpectedForThisPondNumber  + ". Test FAIL");
                testsAllPassed = false;

                if (!thisTestFailed) {
                    failingTests.add(testName);
                    numberOfFailingTests++;
                }

                thisTestFailed = true;
                if (count == numberOfPondsExpected)
                    System.out.println("");
            }
        }
    }

    private static void reportTestNameAndPassedTests(boolean thisTestFailed, List<String> passedTests, int passedTestNumber, boolean testIDDisplayed, String testName) {

        if (passedTestNumber > 0 && !thisTestFailed) {
            if (!testIDDisplayed) {
                System.out.println("Test: " + testName);
            }

            for (int index = 0; index < passedTests.size(); index++){
                System.out.println(passedTests.get(index));
            }
        }
    }

    public static Hashtable mainCodeLogic(List<List<Integer>> multiDimensionalArray) {

        // Set up the array which we'll use to store information about the 'ponds' found
        int numberOfRows = multiDimensionalArray.size();
        //System.out.println("numberOfRows: " + numberOfRows);
        int rowWidthInCells = multiDimensionalArray.get(0).size();
        List<List<Integer>> multiDimensionalIntegerArray = setupIntegerList(numberOfRows, rowWidthInCells);

        int pondNumber = 1;
        boolean increasePondNumber=true;
        boolean firstPondFound = false;

        for (int rowCount = 0; rowCount < numberOfRows; rowCount++){

            for (int cellCount = 0; cellCount < rowWidthInCells; cellCount++) {

                int lowestPondNumberFound = 0;
                boolean checkAllCellsForLowerPondNumber = false;

                // Move onto the next cell if it isn't zero
                if (!isCellZero(rowCount, cellCount, multiDimensionalArray)){
                    if (increasePondNumber && firstPondFound){
                        pondNumber++;
                        increasePondNumber = false;
                    }
                    continue;
                }

                // Are we on the first row
                if (rowCount==0){
                    multiDimensionalIntegerArray.get(rowCount).set(cellCount,pondNumber);
                    if (cellCount == (rowWidthInCells-1)){
                        pondNumber++;
                        increasePondNumber = false;
                    }
                    else
                        increasePondNumber = true;

                    firstPondFound = true;
                    continue;
                }

                // Where we have more than one data row
                if (rowCount>0) {

                    // Firstly, check if the cell above is a known pond
                    if (replacePondOccurencesIfRequired_CellAbove(multiDimensionalIntegerArray, pondNumber, rowCount, cellCount))
                        continue;

                    // Check if the cell diagonally above and to the right is a known pond
                    // First of all check we're not dealing with the last cell
                    int pondNumberToSetToLowerValueIfRequired = 0;
                    if (cellCount != (rowWidthInCells-1)){
                        int pondNumberDirectlyAboveAndToTheRight = multiDimensionalIntegerArray.get(rowCount -1).get(cellCount+1);
                        if (pondNumberDirectlyAboveAndToTheRight >0){

                            // We've joined with a previously existing pond with a smaller ID number
                            // Swap out all cells with the current pondNumber with the smaller pond number
                            if (pondNumberDirectlyAboveAndToTheRight < pondNumber){
                                multiDimensionalIntegerArray.get(rowCount).set(cellCount,pondNumber);
                                replaceAllPondNumberOccurences(pondNumber, pondNumberDirectlyAboveAndToTheRight, multiDimensionalIntegerArray);
                                lowestPondNumberFound = pondNumberDirectlyAboveAndToTheRight;
                                checkAllCellsForLowerPondNumber = true;
                                pondNumberToSetToLowerValueIfRequired = pondNumberDirectlyAboveAndToTheRight;
                            }

                            // We've joined with a previously existing pond with a higher ID number
                            // Swap out all cells with the higher pondNumber with the smaller pond number
                            else{
                                replaceAllPondNumberOccurences(pondNumberDirectlyAboveAndToTheRight, pondNumber, multiDimensionalIntegerArray);
                            }
                            if (!checkAllCellsForLowerPondNumber)
                                continue;
                        }
                    }

                    // Cellcount > 1
                    if (cellCount > 0) {
                        // Check to see if the cell to the immediate left is a pond
                        int pondNumberDirectlyToTheleft = multiDimensionalIntegerArray.get(rowCount).get(cellCount -1);
                        if (pondNumberDirectlyToTheleft >0){
                            multiDimensionalIntegerArray.get(rowCount).set(cellCount,pondNumberDirectlyToTheleft);
                            if (checkAllCellsForLowerPondNumber){
                                replaceAllPondNumberOccurences(pondNumberToSetToLowerValueIfRequired, pondNumberDirectlyToTheleft, multiDimensionalIntegerArray);
                            }
                            //increasePondNumber = true;
                            firstPondFound = true;

                            // We're at the end of the row, therefore we need to increase PondNumber.
                            if (cellCount == rowWidthInCells-1){
                                pondNumber++;
                            }
                            continue;
                        }

                        // Check to see if the cell diagonally to the left of this cell is a pond
                        int pondNumberDiagonallyUpAndToTheleft = multiDimensionalIntegerArray.get(rowCount -1).get(cellCount -1);
                        if (replacePondNumberOccurencesAsRequired_LeftDiagonal(multiDimensionalIntegerArray, pondNumber, rowCount, cellCount, lowestPondNumberFound, checkAllCellsForLowerPondNumber, pondNumberDiagonallyUpAndToTheleft))
                            continue;
                    }

                    // Continue when checkAllCellsForLowerPondNumber is true as the pondNumber has already been set to the cell by this point
                    if (checkAllCellsForLowerPondNumber)
                        continue;

                    // Aha we have a new pond
                    multiDimensionalIntegerArray.get(rowCount).set(cellCount,pondNumber);
                    increasePondNumber = true;
                    firstPondFound = true;
                    if (cellCount == rowWidthInCells-1){
                        pondNumber++;
                    }
                }
            }
        }

        // Parse the array which contains information about different ponds
        // Populate a hashtable with the quantity of each pond number. E.g. there might be 3 cells that correspond to pond 1, 2 for pond 2 etc
        Hashtable hashTable = parseArrayToCreateHashtable(numberOfRows, rowWidthInCells, multiDimensionalIntegerArray);
        return hashTable;
    }

    private static boolean replacePondOccurencesIfRequired_CellAbove(List<List<Integer>> multiDimensionalIntegerArray, int pondNumber, int rowCount, int cellCount) {
        int pondNumberDirectlyAbove = multiDimensionalIntegerArray.get(rowCount -1).get(cellCount);
        if (pondNumberDirectlyAbove >0){
            if (pondNumberDirectlyAbove < pondNumber && pondNumber!=pondNumberDirectlyAbove){
                multiDimensionalIntegerArray.get(rowCount).set(cellCount,pondNumber);
                replaceAllPondNumberOccurences(pondNumber, pondNumberDirectlyAbove, multiDimensionalIntegerArray);
            }
            return true;
        }
        return false;
    }

    private static boolean replacePondNumberOccurencesAsRequired_LeftDiagonal(List<List<Integer>> multiDimensionalIntegerArray, int pondNumber, int rowCount, int cellCount, int lowestPondNumberFound, boolean checkAllCellsForLowerPondNumber, int pondNumberDiagonallyUpAndToTheleft) {

        if (pondNumberDiagonallyUpAndToTheleft >0){
            if (checkAllCellsForLowerPondNumber && (lowestPondNumberFound > pondNumberDiagonallyUpAndToTheleft)){
                multiDimensionalIntegerArray.get(rowCount).set(cellCount,pondNumberDiagonallyUpAndToTheleft);
                replaceAllPondNumberOccurences(lowestPondNumberFound, pondNumberDiagonallyUpAndToTheleft, multiDimensionalIntegerArray);
            }
            if (pondNumberDiagonallyUpAndToTheleft < pondNumber){
                multiDimensionalIntegerArray.get(rowCount).set(cellCount,pondNumberDiagonallyUpAndToTheleft);
                replaceAllPondNumberOccurences(pondNumber, pondNumberDiagonallyUpAndToTheleft, multiDimensionalIntegerArray);
            }
            else{
                replaceAllPondNumberOccurences(pondNumberDiagonallyUpAndToTheleft, pondNumber, multiDimensionalIntegerArray);
            }
            return true;
        }
        return false;
    }

    private static Hashtable parseArrayToCreateHashtable(int numberOfRows, int rowWidthInCells, List<List<Integer>> multiDimensionalIntegerArray) {
        Hashtable hashTable = new Hashtable<>();
        for (int rowCount = 0; rowCount < numberOfRows; rowCount++){

            for (int cellCount = 0; cellCount < rowWidthInCells; cellCount++) {
                int cellValue = multiDimensionalIntegerArray.get(rowCount).get(cellCount);
                if (cellValue==0)
                    continue;

                if (!hashTable.containsKey(cellValue)){
                    hashTable.put(cellValue, 1);
                }
                else {
                    int quantityOfPonds = (int)hashTable.get(cellValue);
                    quantityOfPonds++;
                    hashTable.put(cellValue, quantityOfPonds);
                }
            }
        }
        return hashTable;
    }

    private static void replaceAllPondNumberOccurences(int pondNumberToBeReplaced, int newPondNumber, List<List<Integer>> multiDimensionalIntegerArray) {

        for (int rowCount =0; rowCount<multiDimensionalIntegerArray.size(); rowCount++){
            for (int cellCount = 0; cellCount < multiDimensionalIntegerArray.get(0).size(); cellCount++ ){
                int cellValue = multiDimensionalIntegerArray.get(rowCount).get(cellCount);
                if (cellValue==pondNumberToBeReplaced){
                    multiDimensionalIntegerArray.get(rowCount).set(cellCount, newPondNumber);
                }
            }
        }
    }

    private static List<List<Integer>> setupIntegerList(int numberOfRows, int arrayWidth) {
        List<List<Integer>> multiDimensionalIntegerArray = new ArrayList<>();

        for (int count = 0; count < numberOfRows; count++){
            List<Integer> integerRow = new ArrayList<>();
            for (int count2 = 0; count2 < arrayWidth; count2++){
                integerRow.add(0);
            }
            multiDimensionalIntegerArray.add(integerRow);
        }
        //System.out.println("multiDimensionalIntegerArray size: " + multiDimensionalIntegerArray.get(0).size());

        return multiDimensionalIntegerArray;
    }

    private static boolean isCellZero(int rowNumber, int cellNumber, List<List<Integer>> multiDimensionalArray){
        if (multiDimensionalArray.get(rowNumber).get(cellNumber)==0)
            return true;
        else
            return false;
    }

    private static void setUpArrays2Rows(List<List<Integer>> multiDimensionalArray, List<List<Integer>> multiDimensionalResultsListArray, List<Integer> row0, List<Integer> row1, List<Integer> resultsPond1, List<Integer> resultsPond2) {

        multiDimensionalArray.add(row0);
        multiDimensionalArray.add(row1);

        multiDimensionalResultsListArray.add(resultsPond1);
        multiDimensionalResultsListArray.add(resultsPond2);
    }

    private static void setUpArrays3Rows(List<List<Integer>> multiDimensionalArray, List<List<Integer>> multiDimensionalResultsListArray, List<Integer> row0, List<Integer> row1, List<Integer> row2, List<Integer> resultsPond1, List<Integer> resultsPond2, List<Integer> resultsPond3) {

        multiDimensionalArray.add(row0);
        multiDimensionalArray.add(row1);
        multiDimensionalArray.add(row2);

        multiDimensionalResultsListArray.add(resultsPond1);
        multiDimensionalResultsListArray.add(resultsPond2);
        multiDimensionalResultsListArray.add(resultsPond3);
    }

    public static String TestCase1(List<List<Integer>> multiDimensionalArray, List<List<Integer>> multiDimensionalResultsListArray) {

        String testName = "Test Case 1";
        List<Integer> row0 = Arrays.asList(0,1,1,1);
        List<Integer> row1 = Arrays.asList(0,1,0,0);

        List<Integer> resultsPond1 = Arrays.asList(2);
        List<Integer> resultsPond2 = Arrays.asList(2);

        setUpArrays2Rows(multiDimensionalArray, multiDimensionalResultsListArray, row0, row1, resultsPond1, resultsPond2);

        return testName;
    }


    private static String TestCase2(List<List<Integer>> multiDimensionalArray, List<List<Integer>> multiDimensionalResultsListArray) {

        String testName = "Test Case 2";

        List<Integer> row0 = Arrays.asList(0,1,1,1);
        List<Integer> row1 = Arrays.asList(0,1,0,0);
        List<Integer> row2 = Arrays.asList(0,1,1,1);

        List<Integer> resultsPond1 = Arrays.asList(3);
        List<Integer> resultsPond2 = Arrays.asList(2);
        List<Integer> resultsPond3 = Arrays.asList(0);

        setUpArrays3Rows(multiDimensionalArray, multiDimensionalResultsListArray, row0, row1, row2, resultsPond1, resultsPond2, resultsPond3);

        return testName;
    }

    private static String TestCase3(List<List<Integer>> multiDimensionalSourceDataArray, List<List<Integer>> multiDimensionalResultsListArray) {

        String testName = "Test Case 3";
        List<Integer> row0 = Arrays.asList(0,0);
        List<Integer> row1 = Arrays.asList(0,1);

        List<Integer> resultsPond1 = Arrays.asList(3);
        List<Integer> resultsPond2 = Arrays.asList(0);

        setUpArrays2Rows(multiDimensionalSourceDataArray, multiDimensionalResultsListArray, row0, row1, resultsPond1, resultsPond2);

        return testName;
    }
    private static String TestCase4(List<List<Integer>> multiDimensionalSourceDataArray, List<List<Integer>> multiDimensionalResultsListArray) {

        String testName = "Test Case 4";
        List<Integer> row0 = Arrays.asList(0,1);
        List<Integer> row1 = Arrays.asList(0,1);

        List<Integer> resultsPond1 = Arrays.asList(2);
        List<Integer> resultsPond2 = Arrays.asList(0);

        setUpArrays2Rows(multiDimensionalSourceDataArray, multiDimensionalResultsListArray, row0, row1, resultsPond1, resultsPond2);

        return testName;
    }

    private static String TestCase5(List<List<Integer>> multiDimensionalSourceDataArray, List<List<Integer>> multiDimensionalResultsListArray) {

        String testName = "Test Case 5";
        List<Integer> row0 = Arrays.asList(0,1,0,0);
        List<Integer> row1 = Arrays.asList(1,0,1,1);

        List<Integer> resultsPond1 = Arrays.asList(4);
        List<Integer> resultsPond2 = Arrays.asList(0);

        setUpArrays2Rows(multiDimensionalSourceDataArray, multiDimensionalResultsListArray, row0, row1, resultsPond1, resultsPond2);

        return testName;
    }

    private static String TestCase6(List<List<Integer>> multiDimensionalSourceDataArray, List<List<Integer>> multiDimensionalResultsListArray) {

        String testName = "Test Case 6";
        List<Integer> row0 = Arrays.asList(1,1,0,0);
        List<Integer> row1 = Arrays.asList(1,0,1,1);

        List<Integer> resultsPond1 = Arrays.asList(3);
        List<Integer> resultsPond2 = Arrays.asList(0);

        setUpArrays2Rows(multiDimensionalSourceDataArray, multiDimensionalResultsListArray, row0, row1, resultsPond1, resultsPond2);

        return testName;
    }

    private static String TestCase7(List<List<Integer>> multiDimensionalSourceDataArray, List<List<Integer>> multiDimensionalResultsListArray) {

        String testName = "Test Case 7";
        List<Integer> row0 = Arrays.asList(0,1,0);
        List<Integer> row1 = Arrays.asList(1,0,1);
        List<Integer> row2 = Arrays.asList(0,1,0);

        List<Integer> resultsPond1 = Arrays.asList(5);
        List<Integer> resultsPond2 = Arrays.asList(0);
        List<Integer> resultsPond3 = Arrays.asList(0);

        setUpArrays3Rows(multiDimensionalSourceDataArray, multiDimensionalResultsListArray, row0, row1, row2, resultsPond1, resultsPond2, resultsPond3);

        return testName;
    }

    private static String TestCase8(List<List<Integer>> multiDimensionalSourceDataArray, List<List<Integer>> multiDimensionalResultsListArray) {

        String testName = "Test Case 8";
        List<Integer> row0 = Arrays.asList(0);
        List<Integer> row1 = Arrays.asList(0);

        List<Integer> resultsPond1 = Arrays.asList(2);
        List<Integer> resultsPond2 = Arrays.asList(0);

        setUpArrays2Rows(multiDimensionalSourceDataArray, multiDimensionalResultsListArray, row0, row1, resultsPond1, resultsPond2);

        return testName;
    }

    private static String TestCase9(List<List<Integer>> multiDimensionalSourceDataArray, List<List<Integer>> multiDimensionalResultsListArray) {

        String testName = "Test Case 9";

        List<Integer> row0 = Arrays.asList(0,1,0,1);
        List<Integer> row1 = Arrays.asList(0,1,1,0);
        List<Integer> row2 = Arrays.asList(0,1,1,1);

        List<Integer> resultsPond1 = Arrays.asList(3);
        List<Integer> resultsPond2 = Arrays.asList(2);
        List<Integer> resultsPond3 = Arrays.asList(0);

        setUpArrays3Rows(multiDimensionalSourceDataArray, multiDimensionalResultsListArray, row0, row1, row2, resultsPond1, resultsPond2, resultsPond3);

        return testName;
    }

    private static String TestCase10(List<List<Integer>> multiDimensionalSourceDataArray, List<List<Integer>> multiDimensionalResultsListArray) {

        String testName = "Test Case 10";
        List<Integer> row0 = Arrays.asList(0,1,0,1,1,0);
        List<Integer> row1 = Arrays.asList(0,1,1,0,1,0);
        List<Integer> row2 = Arrays.asList(1,1,1,1,1,1);


        List<Integer> resultsPond1 = Arrays.asList(2);
        List<Integer> resultsPond2 = Arrays.asList(2);
        List<Integer> resultsPond3 = Arrays.asList(2);

        setUpArrays3Rows(multiDimensionalSourceDataArray, multiDimensionalResultsListArray, row0, row1, row2, resultsPond1, resultsPond2, resultsPond3);


        return testName;
    }

    private static String TestCase11(List<List<Integer>> multiDimensionalSourceDataArray, List<List<Integer>> multiDimensionalResultsListArray) {

        String testName = "Test Case 11";
        List<Integer> row0 = Arrays.asList(0,1,0,1);
        List<Integer> row1 = Arrays.asList(0,1,1,0);

        List<Integer> resultsPond1 = Arrays.asList(2);
        List<Integer> resultsPond2 = Arrays.asList(2);

        setUpArrays2Rows(multiDimensionalSourceDataArray, multiDimensionalResultsListArray, row0, row1, resultsPond1, resultsPond2);

        return testName;
    }

    private static String TestCase12(List<List<Integer>> multiDimensionalArray, List<List<Integer>> multiDimensionalResultsListArray) {
//hjr
        String testName = "Test Case 12";

        // original
        List<Integer> row0 = Arrays.asList(0,1,1,0);
        List<Integer> row1 = Arrays.asList(0,0,0,0);
        List<Integer> row2 = Arrays.asList(0,1,1,0);

        // Formatting issue to be sorted with this test case and the results set below:
        /*List<Integer> resultsPond1 = Arrays.asList(8);
        List<Integer> resultsPond2 = Arrays.asList(1);
        List<Integer> resultsPond3 = Arrays.asList(1);*/

        List<Integer> resultsPond1 = Arrays.asList(8);
        List<Integer> resultsPond2 = Arrays.asList(0);
        List<Integer> resultsPond3 = Arrays.asList(0);

        setUpArrays3Rows(multiDimensionalArray, multiDimensionalResultsListArray, row0, row1, row2, resultsPond1, resultsPond2, resultsPond3);

        return testName;
    }

    private static String TestCase13(List<List<Integer>> multiDimensionalArray, List<List<Integer>> multiDimensionalResultsListArray) {

        String testName = "Test Case 13";

        // Should result in a failure because expected numbers for ponds 4 and 5 havent been specified
        List<Integer> row0 = Arrays.asList(0,1,0,1,0);
        List<Integer> row1 = Arrays.asList(1,1,1,1,1);
        List<Integer> row2 = Arrays.asList(1,0,1,0,0);

        List<Integer> resultsPond1 = Arrays.asList(1);
        List<Integer> resultsPond2 = Arrays.asList(1);
        List<Integer> resultsPond3 = Arrays.asList(1);

        setUpArrays3Rows(multiDimensionalArray, multiDimensionalResultsListArray, row0, row1, row2, resultsPond1, resultsPond2, resultsPond3);

        return testName;
    }

    private static String TestCase14(List<List<Integer>> multiDimensionalArray, List<List<Integer>> multiDimensionalResultsListArray) {

        String testName = "Test Case 14";

        List<Integer> row0 = Arrays.asList(0,1,1,1);
        List<Integer> row1 = Arrays.asList(1,1,1,0);
        List<Integer> row2 = Arrays.asList(0,1,1,1);

        List<Integer> resultsPond1 = Arrays.asList(1);
        List<Integer> resultsPond2 = Arrays.asList(1);
        List<Integer> resultsPond3 = Arrays.asList(1);

        setUpArrays3Rows(multiDimensionalArray, multiDimensionalResultsListArray, row0, row1, row2, resultsPond1, resultsPond2, resultsPond3);

        return testName;
    }
}
