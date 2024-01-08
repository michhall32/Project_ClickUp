package pl.hogwarts.requests.task;

import io.restassured.response.Response;
import org.json.JSONObject;
import pl.hogwarts.dto.task.request.CreateTaskRequestDTO;
import pl.hogwarts.dto.task.response.CreateTaskResponseDTO;
import pl.hogwarts.requests.BaseRequest;
import pl.hogwarts.url.ClickupURL;

import static io.restassured.RestAssured.given;

public class CreateTaskRequest {

    public static Response createTask(JSONObject task, String listID) {
        return given()
                .spec(BaseRequest.requestSpec())
                .body(task.toString())
                .when()
                .post(ClickupURL.getTasksUrl(listID))
                .then()
                .log().ifError()
                .extract()
                .response();
    }

    public static CreateTaskResponseDTO createTask(CreateTaskRequestDTO taskDTO, String listID) {
        return given()
                .spec(BaseRequest.requestSpecWithLogs())
                .body(taskDTO)
                .when()
                .post(ClickupURL.getTasksUrl(listID))
                .then()
                .statusCode(200)
                .log().ifError()
                .extract()
                .response()
                .as(CreateTaskResponseDTO.class);
    }
}
