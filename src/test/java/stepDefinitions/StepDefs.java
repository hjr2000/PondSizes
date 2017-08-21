package stepDefinitions;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.junit.AfterClass;
import org.junit.jupiter.api.AfterAll;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import static com.qualicity.Main.TestCase1;
import static com.qualicity.Main.mainCodeLogic;
import static com.qualicity.Main.reportResults;

public class StepDefs {

    List<List<Integer>> multiDimensionalSourceDataArray = new ArrayList<>();
    List<List<Integer>> multiDimensionalResultsListArray = new ArrayList<>();
    Scenario scenario;

    @Before
    public void before(Scenario scenario) {
        this.scenario = scenario;
    }

    @Given("^I have a test matrix as follows$")
    public void i_have_a_test_matrix_as_follows(DataTable arg1) throws Throwable {

        //System.out.println(arg1.toString());
        System.out.println("+++++++++++++++++++++++++ " + scenario.getName());

        List<List<String>> dataMatrix = arg1.raw();

  /*      System.out.println("Number of rows: " + dataMatrix.size());
        System.out.println("Matrix width: " + dataMatrix.get(0).size());*/

        for (int count = 0; count < dataMatrix.size(); count++){
            List<Integer> intRowList = new ArrayList<>();
            for(String s : dataMatrix.get(count)){
                intRowList.add(Integer.valueOf(s));
            }
            multiDimensionalSourceDataArray.add(intRowList);
        }
    }

    @Given("^expected pond results as follows$")
    public void expected_pond_results_as_follows(DataTable arg1) throws Throwable {
        List<List<String>> dataMatrix = arg1.raw();

        for (int count = 0; count < dataMatrix.size(); count++){
            List<Integer> intRowList = new ArrayList<>();
            /*for(String s : dataMatrix.get(count)){
                intRowList.add(Integer.valueOf(s));
            }*/
            String s = dataMatrix.get(count).get(1);
            intRowList.add(Integer.valueOf(s));
            multiDimensionalResultsListArray.add(intRowList);
        }
    }

    @Then("^the correct numbers of each pond numbers are found$")
    public void the_correct_numbers_of_each_pond_numbers_are_found() throws Throwable {
        //String testName1 = TestCase1(multiDimensionalSourceDataArray, multiDimensionalResultsListArray);
        String testName1  = "Test Case 1 - Gherkin";
        Hashtable hashTable = mainCodeLogic(multiDimensionalSourceDataArray);
        reportResults(multiDimensionalResultsListArray, testName1, hashTable);
    }




}
