package ru.levelp.at.lesson0709.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class PersonData {

    private String id;
    private String role;
    private String email;
    private String phoneNumber;
    private String placeOfWork;
    private IdentityData identity;

    @JsonProperty("address")
    private AddressData addressData;
}
