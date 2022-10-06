package ru.levelp.at.lesson0708.api.model;

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
public class PassportData {

    private String series;
    private String number;
    private String placeOfIssue;
    private String dateOfIssue;
    private String departmentCode;
}
