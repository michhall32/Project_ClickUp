package pl.hogwarts.requests.task;

import io.restassured.response.Response;
import org.json.JSONObject;
import pl.hogwarts.requests.BaseRequest;
import pl.hogwarts.url.ClickupURL;

import static io.restassured.RestAssured.given;

public class UpdateTaskRequest {

    public static Response updateTask(JSONObject updateTask, String taskID) {

        return given()
                .spec(BaseRequest.requestSpecWithLogs())
                .body(updateTask.toString())
                .when()
                .put(ClickupURL.getTaskUrl(taskID))
                .then()
                .statusCode(200)
                .log().ifError()
                .extract()
                .response();
    }
}
