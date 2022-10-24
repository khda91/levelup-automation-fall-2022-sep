package ru.levelp.at.lesson12.design.patterns.builder;

import java.time.LocalDate;

public class PersonWithBuilder {

    private final String firstName;
    private final String lastName;
    private final LocalDate dateOfBirth;
    private final Integer salary;

    public PersonWithBuilder(String firstName, String lastName, LocalDate dateOfBirth, Integer salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.salary = salary;
    }

    public static Builder builder() {
        return new Builder();
    }

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

    @Override
    public String toString() {
        return "PersonWithBuilder{"
            + "firstName='" + firstName
            + "\', lastName='" + lastName
            + "\', dateOfBirth=" + dateOfBirth
            + ", salary=" + salary
            + '}';
    }

    public static class Builder {
        private String firstName;
        private String lastName;
        private LocalDate dateOfBirth;
        private Integer salary;

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder dateOfBirth(LocalDate dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public Builder salary(Integer salary) {
            this.salary = salary;
            return this;
        }

        public PersonWithBuilder build() {
            return new PersonWithBuilder(firstName, lastName, dateOfBirth, salary);
        }
    }
}
