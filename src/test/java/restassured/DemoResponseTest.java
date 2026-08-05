package restassured;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.hamcrest.Matchers.*;


public class DemoResponseTest {

    static final String RATE_LIMIT_EP = "https://api.github.com/rate_limit";
    static final String SEARCH_EP = "https://api.github.com/search/repositories";

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

    @Test
    void validateResponseFields(){
        RestAssured
                .get(RATE_LIMIT_EP)
                .then()
                .rootPath("resources.code_search")
                    .body("limit", greaterThanOrEqualTo(60))
                    .body("remaining", lessThanOrEqualTo(60))
                .rootPath("resources.search")
                    .body("limit", greaterThanOrEqualTo(10))
                    .body("remaining", lessThanOrEqualTo(10))
        ;
    }

    @Test
    void validateRepeatingResponseFields(){
        RestAssured
                .given()
                .params(Map.of("q", "java", "per_page", "2"))
                .get(SEARCH_EP)
                .then()
                .body("items.owner.user_view_type", hasItem("public"))
        ;
    }

    @Test
    void validateHeadMethod(){
        RestAssured
                .when()
                .head("https://api.github.com")
                .then()
                .body(emptyOrNullString())
        ;
    }
}
