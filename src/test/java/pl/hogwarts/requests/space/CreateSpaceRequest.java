package pl.hogwarts.requests.space;

import io.restassured.response.Response;
import org.json.JSONObject;
import pl.hogwarts.properties.ClickUpProperties;
import pl.hogwarts.requests.BaseRequest;
import pl.hogwarts.url.ClickupURL;

import static io.restassured.RestAssured.given;

public class CreateSpaceRequest {

    public static Response createSpace(JSONObject space) {
        return given()
                .spec(BaseRequest.requestSpec())
                .body(space.toString())
                .when()
                .post(ClickupURL.getSpacesUrl(ClickUpProperties.getTeamId()))
                .then()
                .log().ifError()
                .extract()
                .response();
    }

}
