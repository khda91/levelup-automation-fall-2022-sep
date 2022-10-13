package ru.levelp.at.lesson1011.cicd;

import com.google.common.io.Files;
import io.qameta.allure.Step;
import java.io.File;
import java.nio.charset.StandardCharsets;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class JsonAllureTest {

    @BeforeAll
    static void beforeAll() {
        new EnvironmentResourcesGenerator().createProperties();
    }

    @Test
    void test() {
        step();
        readJson(new File(this.getClass()
                              .getResource("/ru/levelp/at/lesson0709/api/requests/createUserJson.json")
                              .getPath()));
    }

    @SneakyThrows
    @Step("Считать json")
    String readJson(File file) {
        return String.join("", Files.readLines(file, StandardCharsets.UTF_8));
    }

    @Step("Some step")
    void step() {

    }
}
