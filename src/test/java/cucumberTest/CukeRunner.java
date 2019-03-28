package cucumberTest;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/Feature",
        glue = "stepDefinitions",
        //monochrome = true,
        plugin = {"pretty", "html:target/cucumber"}
)

public class CukeRunner {
}
