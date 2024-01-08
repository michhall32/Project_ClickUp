package pl.hogwarts.requests.list;

import io.restassured.response.Response;
import org.json.JSONObject;
import pl.hogwarts.requests.BaseRequest;
import pl.hogwarts.url.ClickupURL;

import static io.restassured.RestAssured.given;

public class CreateListRequest {

    public static Response createList(JSONObject list, String spaceID) {
        return given()
                .spec(BaseRequest.requestSpec())
                .body(list.toString())
                .when()
                .post(ClickupURL.getListsUrl(spaceID))
                .then()
                .log().ifError()
                .extract()
                .response();
    }

}
