package pl.hogwarts.tests.space;

import io.restassured.path.json.JsonPath;
import org.assertj.core.api.Assertions;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.hogwarts.requests.space.CreateSpaceRequest;
import pl.hogwarts.requests.space.DeleteSpaceRequest;
import pl.hogwarts.requests.space.UpdateSpaceRequest;

class CreateSpaceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreateSpaceTest.class);
    private static final String SPACE_NAME = "Mine space nejme from java";
    private static final String UPDATED_SPACE_NAME = "Nowka sztuka niesmigana nejme";
    private String spaceID;

    @Test
    void updateSpaceTest() {

        spaceID = createSpaceStep();
        LOGGER.info("Space created with ID: {}", spaceID);

        updateSpaceStep();
        LOGGER.info("Updated space with ID: {}", spaceID);

        deleteSpaceStep();
        LOGGER.info("Deleted space with ID: {}", spaceID);

    }

    private String createSpaceStep() {

        JSONObject space = new JSONObject();
        space.put("name", SPACE_NAME);

        final var response = CreateSpaceRequest.createSpace(space);
        Assertions.assertThat(response.statusCode()).isEqualTo(200);

        JsonPath jsonData = response.jsonPath();
        Assertions.assertThat(jsonData.getString("name")).isEqualTo(SPACE_NAME);

        return jsonData.getString("id");
    }

    private void updateSpaceStep(){

        JSONObject updateSpace = new JSONObject();
        updateSpace.put("name", UPDATED_SPACE_NAME);
        updateSpace.put("color", "#ee6868");

        final var response = UpdateSpaceRequest.updateSpace(updateSpace, spaceID);
        Assertions.assertThat(response.statusCode()).isEqualTo(200);

        JsonPath jsonData = response.jsonPath();
        Assertions.assertThat(jsonData.getString("name")).isEqualTo(UPDATED_SPACE_NAME);
        Assertions.assertThat(jsonData.getString("color")).isEqualTo("#ee6868");

    }

    private void deleteSpaceStep(){

        final var response = DeleteSpaceRequest.deleteSpace(spaceID);
        Assertions.assertThat(response.statusCode()).isEqualTo(200);

    }
}
