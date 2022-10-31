package ru.levelp.at.lesson15.suites;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import ru.levelp.at.lesson0709.api.client.NegativePeopleClientTest;
import ru.levelp.at.lesson0709.api.client.PeopleClientTest;

@Suite
@SelectPackages("ru/levelp/at/lesson0709/api/client")
@SelectClasses({NegativePeopleClientTest.class, PeopleClientTest.class})
public class PositiveSuite {
}
