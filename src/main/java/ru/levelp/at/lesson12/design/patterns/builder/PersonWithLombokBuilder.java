package ru.levelp.at.lesson12.design.patterns.builder;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class PersonWithLombokBuilder {

    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private Integer salary;
}
