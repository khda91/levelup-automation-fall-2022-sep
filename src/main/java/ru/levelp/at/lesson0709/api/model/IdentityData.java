package ru.levelp.at.lesson0709.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class IdentityData {

    private String firstName;
    private String lastName;
    private String middleName;
    private String gender;
    private String dateOfBirth;
    private String placeOfBirth;
    private PassportData passport;
}
