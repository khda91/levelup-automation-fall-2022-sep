package ru.levelp.at.lesson12.design.patterns.builder;

import com.github.javafaker.Faker;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

public class PersonBuilderTest {

    @Test
    void builderTest() {
        var faker = new Faker();
        var vasya = PersonWithBuilder.builder()
                                     .firstName(faker.name().firstName())
                                     .lastName(faker.name().lastName())
                                     .dateOfBirth(LocalDate.now().minusYears(20)
                                                           .minusMonths(5))
                                     .salary(1500)
                                     .build();
        System.out.println(vasya);

        var olya = PersonWithBuilder.builder()
                                    .firstName(faker.name().firstName())
                                    .build();

        System.out.println(olya);

        var empty = PersonWithBuilder.builder().build();
        System.out.println(empty);

        var ivanov = PersonWithBuilder.builder()
                                      .lastName(faker.name().lastName())
                                      .build();

        System.out.println(ivanov);
    }

    @Test
    void lombokBuilderTest() {
        var faker = new Faker();
        var vasya = PersonWithLombokBuilder.builder()
                                           .firstName(faker.name().firstName())
                                           .lastName(faker.name().lastName())
                                           .dateOfBirth(LocalDate.now().minusYears(20)
                                                                 .minusMonths(5))
                                           .salary(1500)
                                           .build();
        System.out.println(vasya);

        var olya = PersonWithLombokBuilder.builder()
                                          .firstName(faker.name().firstName())
                                          .build();

        System.out.println(olya);

        var empty = PersonWithLombokBuilder.builder().build();
        System.out.println(empty);

        var ivanov = PersonWithLombokBuilder.builder()
                                            .lastName(faker.name().lastName())
                                            .build();

        System.out.println(ivanov);
    }
}
