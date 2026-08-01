package restassured;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DemoTest {
    static final String BASE_URL = "https://api.github.com";
    @Test
    void someTest(){
        System.out.println("test");
        RestAssured.get(BASE_URL)
                .then()
                .statusCode(200);
    }

    @Test
    void somePrettyPeekest(){
        RestAssured.get(BASE_URL)
                .prettyPeek()
                .then()
                .statusCode(200);
    }

    @Test
    void exploreResponseObject(){
        Response response = RestAssured.get(BASE_URL);
        assertEquals(200, response.getStatusCode());
    }

    @Test
    void hamcrestMatchers(){
        RestAssured.get(BASE_URL)
                .then()
                .statusCode(lessThan(300))
                .header("etag", notNullValue());
    }


}
