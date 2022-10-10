package ru.levelp.at.lesson0709.api.database.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class Person {
    private UUID id;
    private String email;
    // phoneNumber => phone_number
    private String phoneNumber;
    private String role;
    private String firstName;
    private String lastName;
    private String middleName;
    private String gender;
    private LocalDate dateOfBirth;
    private String placeOfBirth;
    private String placeOfWork;
    private Integer passportSeries;
    private Integer passportNumber;
    private String placeOfIssue;
    private LocalDate dateOfIssue;
    private String departmentCode;
    private String street;
    private Integer houseNumber;
    private Integer houseBuilding;
    private String houseLetter;
    private Integer flat;
    private String city;
    private String postalCode;
}
