package Runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

				@CucumberOptions(
				features= "src/test/java/Features",
				glue= {"StepDefinitions"},
				plugin = {"pretty"},
				monochrome = true
				)
		
		public class RunTest extends AbstractTestNGCucumberTests { 
			
	}


