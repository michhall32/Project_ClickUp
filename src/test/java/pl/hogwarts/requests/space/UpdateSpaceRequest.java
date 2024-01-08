package pl.hogwarts.requests.space;

import io.restassured.response.Response;
import org.json.JSONObject;
import pl.hogwarts.requests.BaseRequest;
import pl.hogwarts.url.ClickupURL;

import static io.restassured.RestAssured.given;

public class UpdateSpaceRequest {


    public static Response updateSpace(JSONObject updateSpace, String spaceID) {
        return given()
                .spec(BaseRequest.requestSpec())
                .body(updateSpace.toString())
                .when()
                .put(ClickupURL.getSpaceUrl(spaceID))
                .then()
                .log().ifError()
                .extract()
                .response();
    }

}
