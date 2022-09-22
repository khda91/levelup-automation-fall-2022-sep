package ru.levelp.at.lesson0304.unit.testing.testng.list.converter.suites;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import ru.levelp.at.lesson0304.unit.testing.testng.list.converter.hooks.AllHooksIT;
import ru.levelp.at.lesson0304.unit.testing.testng.list.converter.hooks.with.lifepercycle.PositiveHooksIT;
import ru.levelp.at.lesson0304.unit.testing.testng.list.converter.hooks.without.lifepercycle.PositiveHooksTest;

@Suite
@SelectClasses({AllHooksIT.class, PositiveHooksTest.class, PositiveHooksIT.class})
public class ClassSuite {
}
