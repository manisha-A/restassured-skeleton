package restassured;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class DemoOtherMethodsTest {
    static final String REPO_EP = "https://api.github.com/user/repos";
    // replace the value with actual token
    static final String TOKEN = "token";

    @Disabled("disabled test")
    @Test
    void createRepoValidation(){
        RestAssured
                .given()
                .auth()
                .oauth2(TOKEN)
                .when()
                .body("{\"name\":\"this-is-test-repo\"}")
                .post(REPO_EP)
                .then()
                .statusCode(201);
    }

}
