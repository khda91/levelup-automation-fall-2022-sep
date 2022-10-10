package ru.levelp.at.lesson0709.api.database.spring;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;
import org.apache.http.HttpStatus;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import ru.levelp.at.lesson0709.api.client.PeopleClient;
import ru.levelp.at.lesson0709.api.model.CreatePersonData;
import ru.levelp.at.lesson0709.api.model.IdentityData;
import ru.levelp.at.lesson0709.api.model.PassportData;
import ru.levelp.at.lesson0709.api.model.PersonResponse;

public class PeopleClientDatabaseTest {

    private PeopleClient peopleClient;
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
        RestAssured.basePath = "/srv-person-profile";

        var requestSpecification = new RequestSpecBuilder()
            .log(LogDetail.ALL)
            .setContentType(ContentType.JSON)
            .build();

        var responseSpecification = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .build();

        RestAssured.requestSpecification = requestSpecification;
        RestAssured.responseSpecification = responseSpecification;

        peopleClient = new PeopleClient();
        jdbcTemplate = new JdbcTemplate(DataSource.getDataSource());
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(DataSource.getDataSource());
    }

    @Test
    void createPersonStatement() {
        var faker = new Faker();
        var createPersonData = CreatePersonData
            .builder()
            .role("WORK_INSPECTOR")
            .email(faker.internet().emailAddress())
            .phoneNumber(faker.phoneNumber().cellPhone())
            .placeOfWork(faker.company().name())
            .identity(IdentityData.builder()
                                  .firstName(faker.name().firstName())
                                  .lastName(faker.name().lastName())
                                  .middleName(faker.name().nameWithMiddle())
                                  .dateOfBirth(LocalDate.now().minusYears(25).toString())
                                  .gender("FEMALE")
                                  .placeOfBirth(faker.country().name())
                                  .passport(PassportData.builder()
                                                        .series("7898")
                                                        .number("401287")
                                                        .dateOfIssue(LocalDate.now().minusYears(20).toString())
                                                        .placeOfIssue(faker.address().fullAddress())
                                                        .departmentCode("785-999")
                                                        .build())
                                  .build())
            .build();

        PersonResponse response = peopleClient.createPerson(createPersonData)
                                              .then()
                                              .statusCode(HttpStatus.SC_CREATED)
                                              .extract()
                                              .as(PersonResponse.class);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(response.getData().getId()).isNotEmpty();
            softly.assertThat(response.getData().getEmail()).isEqualTo(createPersonData.getEmail());
            softly.assertThat(response.getData().getRole()).isEqualTo(createPersonData.getRole());
        });

        var sql = "SELECT * FROM person WHERE id = '" + response.getData().getId() + "'";

        Map<String, Object> result = jdbcTemplate.queryForMap(sql);
        assertThat(result.get("id")).isNotNull();
        assertThat(result.get("email")).isEqualTo(createPersonData.getEmail());
    }

    @Test
    void createPersonPreparedStatement() {
        var faker = new Faker();
        var createPersonData = CreatePersonData
            .builder()
            .role("WORK_INSPECTOR")
            .email(faker.internet().emailAddress())
            .phoneNumber(faker.phoneNumber().cellPhone())
            .placeOfWork(faker.company().name())
            .identity(IdentityData.builder()
                                  .firstName(faker.name().firstName())
                                  .lastName(faker.name().lastName())
                                  .middleName(faker.name().nameWithMiddle())
                                  .dateOfBirth(LocalDate.now().minusYears(25).toString())
                                  .gender("FEMALE")
                                  .placeOfBirth(faker.country().name())
                                  .passport(PassportData.builder()
                                                        .series("7898")
                                                        .number("401287")
                                                        .dateOfIssue(LocalDate.now().minusYears(20).toString())
                                                        .placeOfIssue(faker.address().fullAddress())
                                                        .departmentCode("785-999")
                                                        .build())
                                  .build())
            .build();

        PersonResponse response = peopleClient.createPerson(createPersonData)
                                              .then()
                                              .statusCode(HttpStatus.SC_CREATED)
                                              .extract()
                                              .as(PersonResponse.class);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(response.getData().getId()).isNotEmpty();
            softly.assertThat(response.getData().getEmail()).isEqualTo(createPersonData.getEmail());
            softly.assertThat(response.getData().getRole()).isEqualTo(createPersonData.getRole());
        });

        var sql = "SELECT * FROM person WHERE id = ?";
        Map<String, Object> result =
            jdbcTemplate.queryForMap(sql, UUID.fromString(response.getData().getId()));
        assertThat(result.get("id")).isNotNull();
        assertThat(result)
            .containsEntry("email", createPersonData.getEmail())
            .containsEntry("role", createPersonData.getRole());
    }

    @Test
    void createPersonWithNamedJdbcTemplate() {
        var faker = new Faker();
        var createPersonData = CreatePersonData
            .builder()
            .role("WORK_INSPECTOR")
            .email(faker.internet().emailAddress())
            .phoneNumber(faker.phoneNumber().cellPhone())
            .placeOfWork(faker.company().name())
            .identity(IdentityData.builder()
                                  .firstName(faker.name().firstName())
                                  .lastName(faker.name().lastName())
                                  .middleName(faker.name().nameWithMiddle())
                                  .dateOfBirth(LocalDate.now().minusYears(25).toString())
                                  .gender("FEMALE")
                                  .placeOfBirth(faker.country().name())
                                  .passport(PassportData.builder()
                                                        .series("7898")
                                                        .number("401287")
                                                        .dateOfIssue(LocalDate.now().minusYears(20).toString())
                                                        .placeOfIssue(faker.address().fullAddress())
                                                        .departmentCode("785-999")
                                                        .build())
                                  .build())
            .build();

        PersonResponse response = peopleClient.createPerson(createPersonData)
                                              .then()
                                              .statusCode(HttpStatus.SC_CREATED)
                                              .extract()
                                              .as(PersonResponse.class);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(response.getData().getId()).isNotEmpty();
            softly.assertThat(response.getData().getEmail()).isEqualTo(createPersonData.getEmail());
            softly.assertThat(response.getData().getRole()).isEqualTo(createPersonData.getRole());
        });

        var sql = "SELECT * FROM person WHERE id = :id AND email = :email";
        Map<String, Object> result =
            namedParameterJdbcTemplate.queryForMap(sql, Map.of(
                "email", createPersonData.getEmail(),
                "id", UUID.fromString(response.getData().getId())
            ));
        assertThat(result.get("id")).isNotNull();
        assertThat(result)
            .containsEntry("email", createPersonData.getEmail())
            .containsEntry("role", createPersonData.getRole());
        System.out.println(result);
    }

    @Test
    void createPersonWithDao() {
        var faker = new Faker();
        var createPersonData = CreatePersonData
            .builder()
            .role("WORK_INSPECTOR")
            .email(faker.internet().emailAddress())
            .phoneNumber(faker.phoneNumber().cellPhone())
            .placeOfWork(faker.company().name())
            .identity(IdentityData.builder()
                                  .firstName(faker.name().firstName())
                                  .lastName(faker.name().lastName())
                                  .middleName(faker.name().nameWithMiddle())
                                  .dateOfBirth(LocalDate.now().minusYears(25).toString())
                                  .gender("FEMALE")
                                  .placeOfBirth(faker.country().name())
                                  .passport(PassportData.builder()
                                                        .series("7898")
                                                        .number("401287")
                                                        .dateOfIssue(LocalDate.now().minusYears(20).toString())
                                                        .placeOfIssue(faker.address().fullAddress())
                                                        .departmentCode("785-999")
                                                        .build())
                                  .build())
            .build();

        PersonResponse response = peopleClient.createPerson(createPersonData)
                                              .then()
                                              .statusCode(HttpStatus.SC_CREATED)
                                              .extract()
                                              .as(PersonResponse.class);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(response.getData().getId()).isNotEmpty();
            softly.assertThat(response.getData().getEmail()).isEqualTo(createPersonData.getEmail());
            softly.assertThat(response.getData().getRole()).isEqualTo(createPersonData.getRole());
        });

        var personDao = new PersonDao(DataSource.getDataSource());
        var person = personDao.getPersonById(response.getData().getId());

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(person.getEmail()).isEqualTo(createPersonData.getEmail());
            softly.assertThat(person.getRole()).isEqualTo(createPersonData.getRole());
            softly.assertThat(person.getFirstName()).isEqualTo(createPersonData.getIdentity().getFirstName());
            softly.assertThat(person.getLastName()).isEqualTo(createPersonData.getIdentity().getLastName());
        });

        System.out.println(person);
    }

    @Test
    void createPersonWithDaoAndRowMapper() {
        var faker = new Faker();
        var createPersonData = CreatePersonData
            .builder()
            .role("WORK_INSPECTOR")
            .email(faker.internet().emailAddress())
            .phoneNumber(faker.phoneNumber().cellPhone())
            .placeOfWork(faker.company().name())
            .identity(IdentityData.builder()
                                  .firstName(faker.name().firstName())
                                  .lastName(faker.name().lastName())
                                  .middleName(faker.name().nameWithMiddle())
                                  .dateOfBirth(LocalDate.now().minusYears(25).toString())
                                  .gender("FEMALE")
                                  .placeOfBirth(faker.country().name())
                                  .passport(PassportData.builder()
                                                        .series("7898")
                                                        .number("401287")
                                                        .dateOfIssue(LocalDate.now().minusYears(20).toString())
                                                        .placeOfIssue(faker.address().fullAddress())
                                                        .departmentCode("785-999")
                                                        .build())
                                  .build())
            .build();

        PersonResponse response = peopleClient.createPerson(createPersonData)
                                              .then()
                                              .statusCode(HttpStatus.SC_CREATED)
                                              .extract()
                                              .as(PersonResponse.class);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(response.getData().getId()).isNotEmpty();
            softly.assertThat(response.getData().getEmail()).isEqualTo(createPersonData.getEmail());
            softly.assertThat(response.getData().getRole()).isEqualTo(createPersonData.getRole());
        });

        var personDao = new PersonDao(DataSource.getDataSource());
        var people = personDao.getPeople();

        System.out.println(people);
    }
}
