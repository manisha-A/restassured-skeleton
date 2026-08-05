package restassured;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.hamcrest.Matchers.*;


public class DemoResponseValidation {

    static final String RATE_LIMIT_EP = "https://api.github.com/rate_limit";
    static final String SEARCH_EP = "https://api.github.com/search/repositories";

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
//                .rootPath("items.owner")
                .body("items.owner.user_view_type", hasItem("public"))
        ;
    }
}
