package restassured;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.Map;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DemoTest {
    static final String BASE_URL = "https://api.github.com";
    static final String SEARCH_EP = "https://api.github.com/search/repositories";
    @Test
    void someTest(){
        RestAssured.get(BASE_URL)
                .then()
                .statusCode(200);
    }

    @Test
    void somePrettyPeekest(){
        RestAssured.get(BASE_URL)
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

    @Test
    void canPassParams(){
        RestAssured
                .given()
                .params(Map.of("q", "java", "per_page", "1"))
                .get(BASE_URL + "/search/repositories")
                .then()
                .statusCode(200);
    }

    @ParameterizedTest
    @MethodSource("paramsHashMap")
    void parameterisedTest(Map<String, String> params, int expectedCount){
        var response =
        RestAssured
                .given()
                .params(params)
                .get(SEARCH_EP)
                .jsonPath();
        assertEquals(response.getInt("items.size()"),expectedCount);
    }

    static Object[][] paramsHashMap () {
        return new Object[][]{
                {Map.of("q", "java", "per_page", "1"), 1},
                {Map.of("q", "java", "per_page", "2"), 2}
        };
        };
    }


