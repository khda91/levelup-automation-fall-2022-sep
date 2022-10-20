package ru.levelp.at.lesson12.design.patterns.builder;

import java.time.LocalDate;

public class PersonWithoutBuilder {

    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private Integer salary;

    public PersonWithoutBuilder() {
    }

    public PersonWithoutBuilder(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public PersonWithoutBuilder(String firstName, LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.dateOfBirth = dateOfBirth;
    }

    public PersonWithoutBuilder(LocalDate dateOfBirth, String lastName) {
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    public PersonWithoutBuilder(String lastName) {
        this.lastName = lastName;
    }

    // табу так нельзя так как есть конструктор PersonWithoutBuilder(String lastName)
    // PersonWithoutBuilder(String firstName) сингнатуры совпадают
    //    public PersonWithoutBuilder(String firstName) {
    //        this.firstName = firstName;
    //    }

    //    public PersonWithoutBuilder(String lastName, LocalDate dateOfBirth) {
    //        this.lastName = lastName;
    //        this.dateOfBirth = dateOfBirth;
    //    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public Integer getSalary() {
        return salary;
    }
}
