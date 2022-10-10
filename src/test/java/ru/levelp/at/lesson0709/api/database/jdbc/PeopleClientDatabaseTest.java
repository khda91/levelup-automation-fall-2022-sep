package ru.levelp.at.lesson0709.api.database.jdbc;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.UUID;
import org.apache.http.HttpStatus;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.levelp.at.lesson0709.api.client.PeopleClient;
import ru.levelp.at.lesson0709.api.database.jdbc.PersonDao;
import ru.levelp.at.lesson0709.api.database.jdbc.PostgresClient;
import ru.levelp.at.lesson0709.api.model.CreatePersonData;
import ru.levelp.at.lesson0709.api.model.IdentityData;
import ru.levelp.at.lesson0709.api.model.PassportData;
import ru.levelp.at.lesson0709.api.model.PersonResponse;

public class PeopleClientDatabaseTest {

    private PeopleClient peopleClient;

    private PostgresClient postgresClient;

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
        postgresClient = PostgresClient.getInstance();
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

        try (var statement = postgresClient.getConnection().createStatement()) {
            var sql = "SELECT * FROM person WHERE id = '" + response.getData().getId() + "'";
            var rs = statement.executeQuery(sql);

            var sa = new SoftAssertions();
            while (rs.next()) {
                System.out.println(rs.getString("id"));
                sa.assertThat(rs.getString("id")).isNotNull();
            }
            sa.assertAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
        try (var statement = postgresClient.getConnection().prepareStatement(sql)) {
            statement.setObject(1, UUID.fromString(response.getData().getId()));

            var rs = statement.executeQuery();

            var sa = new SoftAssertions();
            while (rs.next()) {
                System.out.println(rs.getString("id"));
                sa.assertThat(rs.getString("id")).isNotNull();
                sa.assertThat(rs.getString("email")).isEqualTo(createPersonData.getEmail());
            }
            sa.assertAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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

        var personDao = new PersonDao(postgresClient.getConnection());
        var person = personDao.getPersonById(response.getData().getId());

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(person.getEmail()).isEqualTo(createPersonData.getEmail());
            softly.assertThat(person.getRole()).isEqualTo(createPersonData.getRole());
            softly.assertThat(person.getFirstName()).isEqualTo(createPersonData.getIdentity().getFirstName());
            softly.assertThat(person.getLastName()).isEqualTo(createPersonData.getIdentity().getLastName());
        });

        System.out.println(person);
    }

    @AfterEach
    void tearDown() {
        PostgresClient.getInstance().close();
        postgresClient = null;
    }
}
