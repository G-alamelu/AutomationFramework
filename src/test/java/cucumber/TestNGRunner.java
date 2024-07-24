package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="C:/Users/alagovin/eclipse-workspace/seleniumprojects/FrameworkDesign/src/test/java/cucumber",
 glue="academy1.stepDefinitions",
 monochrome=true,
 plugin= {"html:target/cucumber.html"})
public class TestNGRunner extends AbstractTestNGCucumberTests{

	
}
