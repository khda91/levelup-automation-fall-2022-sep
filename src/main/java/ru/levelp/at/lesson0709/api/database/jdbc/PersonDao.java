package ru.levelp.at.lesson0709.api.database.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.UUID;
import ru.levelp.at.lesson0709.api.database.model.Person;

public class PersonDao {

    private static final String SELECT_PERSON_BY_ID = "SELECT * FROM person WHERE id = ?";

    private final Connection connection;

    public PersonDao(Connection connection) {
        this.connection = connection;
    }

    public Person getPersonById(String id) {
        var uuid = UUID.fromString(id);
        try (var statement = connection.prepareStatement(SELECT_PERSON_BY_ID)) {
            statement.setObject(1, uuid);
            var rs = statement.executeQuery();
            Person person = null;
            while (rs.next()) {
                person = Person.builder()
                               .id(uuid)
                               .email(rs.getString("email"))
                               .phoneNumber(rs.getString("phone_number"))
                               .role(rs.getString("role"))
                               .firstName(rs.getString("first_name"))
                               .lastName(rs.getString("last_name"))
                               .middleName(rs.getString("middle_name"))
                               .gender(rs.getString("gender"))
                               .dateOfBirth(LocalDate.parse(rs.getString("date_of_birth")))
                               .placeOfBirth(rs.getString("place_of_birth"))
                               .placeOfWork(rs.getString("place_of_work"))
                               .passportSeries(rs.getInt("passport_series"))
                               .passportNumber(rs.getInt("passport_number"))
                               .placeOfIssue(rs.getString("place_of_issue"))
                               .dateOfIssue(LocalDate.parse(rs.getString("date_of_issue")))
                               .departmentCode(rs.getString("department_code"))
                               .street(rs.getString("street"))
                               .houseNumber(rs.getInt("house_number"))
                               .houseBuilding(rs.getInt("house_building"))
                               .houseLetter(rs.getString("house_letter"))
                               .flat(rs.getInt("flat"))
                               .city(rs.getString("city"))
                               .postalCode(rs.getString("postal_code"))
                               .build();
            }
            return person;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
