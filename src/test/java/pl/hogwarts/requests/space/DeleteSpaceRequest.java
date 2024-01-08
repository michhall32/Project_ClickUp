package pl.hogwarts.requests.space;

import io.restassured.response.Response;
import pl.hogwarts.requests.BaseRequest;
import pl.hogwarts.url.ClickupURL;

import static io.restassured.RestAssured.given;

public class DeleteSpaceRequest {

    public static Response deleteSpace(String spaceID) {
        return given()
                .spec(BaseRequest.requestSpec())
                .when()
                .delete(ClickupURL.getSpaceUrl(spaceID))
                .then()
                .log().ifError()
                .extract()
                .response();
    }
}
