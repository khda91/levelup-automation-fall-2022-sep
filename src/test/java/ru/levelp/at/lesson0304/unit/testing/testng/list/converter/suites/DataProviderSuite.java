package ru.levelp.at.lesson0304.unit.testing.testng.list.converter.suites;

import org.junit.platform.suite.api.IncludeClassNamePatterns;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectPackages({"ru.levelp.at.lesson0304.unit.testing.testng.list.converter.data.provider"})
@IncludeClassNamePatterns({"^.*IT$"})
public class DataProviderSuite {
}
