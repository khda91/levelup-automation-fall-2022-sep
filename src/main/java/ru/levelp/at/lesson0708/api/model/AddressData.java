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
public class AddressData {

    private String street;
    private Integer houseNumber;
    private Integer houseBuilding;
    private String houseLetter;
    private Integer flat;
    private String city;
    private String postalCode;
}
