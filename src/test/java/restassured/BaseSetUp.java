package restassured;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class BaseSetUp {
    @BeforeAll
    static void setUp(){
        RestAssured.responseSpecification= new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
    }

    @AfterAll
    static void tearDown(){
        RestAssured.responseSpecification= null;
    }
}
