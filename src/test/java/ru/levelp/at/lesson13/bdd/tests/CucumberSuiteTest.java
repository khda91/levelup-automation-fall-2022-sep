package ru.levelp.at.lesson13.bdd.tests;

//import io.cucumber.junit.platform.engine.Constants;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

@Suite
//@IncludeEngines("cucumber")
@SelectClasspathResource("ru/levelp/at/lesson13/bdd")
//@ConfigurationParameter(key = Constants.GLUE_PROPERTY_NAME, value = "ru.levelp.at.lesson13.bdd")
public class CucumberSuiteTest {
}
