package ru.levelp.at.lesson0709.api.database.spring;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.sql.DataSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import ru.levelp.at.lesson0709.api.database.model.Person;

public class PersonDao {

    private static final String SELECT_PERSON_BY_ID = "SELECT * FROM person WHERE id = :id";
    private static final String SELECT_PERSON = "SELECT * FROM person";

    private static final RowMapper<Person> PERSON_ROW_MAPPER = BeanPropertyRowMapper.newInstance(Person.class);

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public PersonDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public Person getPersonById(String id) {
        var uuid = UUID.fromString(id);
        return namedParameterJdbcTemplate.queryForObject(SELECT_PERSON_BY_ID, Map.of("id", uuid),
            new RowMapper<Person>() {
                @Override
                public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return Person.builder()
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
            });
    }

    public List<Person> getPeople() {
        return jdbcTemplate.query(SELECT_PERSON, PERSON_ROW_MAPPER);
    }
}
