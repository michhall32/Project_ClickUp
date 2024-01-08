package pl.hogwarts.tests.e2e;

import io.restassured.path.json.JsonPath;
import org.assertj.core.api.Assertions;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.hogwarts.dto.task.request.CreateTaskRequestDTO;
import pl.hogwarts.requests.list.CreateListRequest;
import pl.hogwarts.requests.space.CreateSpaceRequest;
import pl.hogwarts.requests.space.DeleteSpaceRequest;
import pl.hogwarts.requests.task.CreateTaskRequest;
import pl.hogwarts.requests.task.UpdateTaskRequest;


class UpdateTaskE2ETest {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateTaskE2ETest.class);
    private static String spaceName = "SPACE E2E";
    private static String listName = "ZADANKA";
    private static String taskName = "zatestowac sobie klikap";
    private static String taskDesc = "wingardium leviooosa, a nie leviosaaaa";
    private String spaceID;
    private String listID;
    private String taskID;

    @Test
    void updateTaskE2ETest() {

        spaceID = createSpaceStep();
        LOGGER.info("Space created with ID: {}", spaceID);

        listID = createListStep();
        LOGGER.info("List created with ID: {}", listID);

        taskID = createTaskStep();
        LOGGER.info("Task created with ID: {}", taskID);

        updateTaskStep();

        closeTaskStep();

        deleteSpaceStep();

    }

    private String createSpaceStep() {

        JSONObject json = new JSONObject();
        json.put("name", spaceName);

        final var response = CreateSpaceRequest.createSpace(json);
        Assertions.assertThat(response.statusCode()).isEqualTo(200);

        JsonPath jsonData = response.jsonPath();
        Assertions.assertThat(jsonData.getString("name")).isEqualTo(spaceName);

        return jsonData.getString("id");
    }

    private String createListStep() {

        JSONObject json = new JSONObject();
        json.put("name", listName);

        final var response = CreateListRequest.createList(json, spaceID);
        Assertions.assertThat(response.statusCode()).isEqualTo(200);

        JsonPath jsonData = response.jsonPath();
        Assertions.assertThat(jsonData.getString("name")).isEqualTo(listName);

        return jsonData.getString("id");
    }

    private String createTaskStep() {

        CreateTaskRequestDTO taskDTO = new CreateTaskRequestDTO();
        taskDTO.setName(taskName);
        taskDTO.setDescription(taskDesc);
        taskDTO.setStatus("to do");

        final var response = CreateTaskRequest.createTask(taskDTO, listID);
        LOGGER.info("CREATE TASK RESPONSE OBJ: {}", response);

        Assertions.assertThat(response.getName()).isEqualTo(taskName);
        Assertions.assertThat(response.getDescription()).isEqualTo(taskDesc);

        return response.getId();
    }

    private void updateTaskStep() {

        // with POJO method PUT automatically sets all other fields to "null" or "false"
        // It is better to use JSONObject here, as it will only change sent fields, leaving the rest without any changes
        JSONObject updateTask = new JSONObject();
        updateTask.put("name", "very much updated name");
        updateTask.put("description", "very much updated description");


        final var response = UpdateTaskRequest.updateTask(updateTask, taskID);
        Assertions.assertThat(response.statusCode()).isEqualTo(200);

        JsonPath jsonData = response.jsonPath();
        Assertions.assertThat(jsonData.getString("name")).isEqualTo("very much updated name");
        Assertions.assertThat(jsonData.getString("description")).isEqualTo("very much updated description");

    }

    private void closeTaskStep() {

        JSONObject closeTask = new JSONObject();
        closeTask.put("status", "complete");

        final var response = UpdateTaskRequest.updateTask(closeTask, taskID);
        Assertions.assertThat(response.statusCode()).isEqualTo(200);

        JsonPath jsonData = response.jsonPath();
        Assertions.assertThat(jsonData.getString("status.status")).isEqualTo("complete");

    }

    private void deleteSpaceStep() {

        final var response = DeleteSpaceRequest.deleteSpace(spaceID);
        Assertions.assertThat(response.statusCode()).isEqualTo(200);

    }
}
