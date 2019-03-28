package stepDefinitions;

import com.qualicity.PondFoundNotExpectedException;
import com.qualicity.PondMissingException;
import com.qualicity.PondTooLargeException;
import com.qualicity.PondTooSmallException;
import cucumber.api.Scenario;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.Before;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import static com.qualicity.MainClass.mainCodeLogic;
import static com.qualicity.MainClass.reportResults;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class StepDefs {

    private List<List<Integer>> multiDimensionalSourceDataArray = new ArrayList<>();
    private List<List<Integer>> multiDimensionalResultsListArray = new ArrayList<>();
    private Scenario scenario;

    @Before
    public void before(Scenario scenario) {
        this.scenario = scenario;
    }

    @Given("^I have a test matrix as follows$")
    public void i_have_a_test_matrix_as_follows(List<List<String>> dataMatrix) {

        System.out.println("------------------------------ Running test " + scenario.getName());

        for (List<String> stringList : dataMatrix) {
            List<Integer> intRowList = new ArrayList<>();
            for (String s : stringList) {
                intRowList.add(Integer.valueOf(s));
            }
            multiDimensionalSourceDataArray.add(intRowList);
        }
    }

    @Given("^expected pond results as follows$")
    public void expected_pond_results_as_follows(List<List<String>> dataMatrix)  {

        for (List<String> stringList : dataMatrix) {
            List<Integer> intRowList = new ArrayList<>();
            String s = stringList.get(1);
            intRowList.add(Integer.valueOf(s));
            multiDimensionalResultsListArray.add(intRowList);
        }
    }

    @Then("^the correct numbers of each pond numbers are found$")
    public void the_correct_numbers_of_each_pond_numbers_are_found() throws Throwable {

        Hashtable hashTable = mainCodeLogic(multiDimensionalSourceDataArray);
        reportResults(multiDimensionalResultsListArray, hashTable);
        System.out.println("    -- test passed");
    }

    @Then("^an unspecified pond will be found$")
    public void unspecified_pond_found() throws Throwable {

        Hashtable hashTable = mainCodeLogic(multiDimensionalSourceDataArray);

        try {
            reportResults(multiDimensionalResultsListArray, hashTable);
        } catch (PondFoundNotExpectedException e) {
            assertThat(e.getMessage(), is("**** Unexpected pond number 2: A pond of size 3 has been found but this pond has not been specified. Test FAIL ****"));
        }
        System.out.println("    -- test passed");
    }

    @Then("^a pond is seen to be missing$")
    public void pond_is_missing() throws Throwable {

        Hashtable hashTable = mainCodeLogic(multiDimensionalSourceDataArray);

        try {
            reportResults(multiDimensionalResultsListArray, hashTable);
        } catch (PondMissingException e) {
            assertThat(e.getMessage(), is("***** Pond number 3 was not found: Was expecting a pond of size 3. Test FAIL ***** "));
        }
        System.out.println("    -- test passed");
    }

    @Then("^a pond is found to be too large$")
    public void pond_too_large() throws Throwable {

        Hashtable hashTable = mainCodeLogic(multiDimensionalSourceDataArray);

        try {
            reportResults(multiDimensionalResultsListArray, hashTable);
        } catch (PondTooLargeException e) {
            assertThat(e.getMessage(), is("***** Pond number 2: Pond found was size 3 but expected size 2. Test FAIL ***** "));
        }
        System.out.println("    -- test passed");
    }

    @Then("^a pond is found to be too small$")
    public void pond_too_small() throws Throwable {

        Hashtable hashTable = mainCodeLogic(multiDimensionalSourceDataArray);

        try {
            reportResults(multiDimensionalResultsListArray, hashTable);
        } catch (PondTooSmallException e) {
            assertThat(e.getMessage(), is("***** Pond number 2: Pond found was size 3 but expected size 4. Test FAIL ***** "));
        }
        System.out.println("    -- test passed");
    }
}