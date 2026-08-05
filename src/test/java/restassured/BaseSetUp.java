package restassured;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class BaseSetUp {
    @BeforeEach
    void setUp(){
        RestAssured.responseSpecification= new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
    }

    @AfterEach
    void tearDown(){
        RestAssured.responseSpecification= null;
    }
}
