package pl.hogwarts.url;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClickupURL {

    private static final String BASE_URL = "https://api.clickup.com/api/v2";
    private static final String TEAM = "/team";
    private static final String SPACE = "/space";
    private static final String LIST = "/list";
    private static final String TASK = "/task";

    public static String getBaseUrl() {
        return BASE_URL;
    }

    public static String getTeamsUrl() {
        return TEAM;
    }

    public static String getTeamUrl(String teamID) {
        return getTeamsUrl() + "/" + teamID;
    }

    public static String getSpacesUrl(String teamID) {
        return getTeamUrl(teamID) + SPACE;
    }

    public static String getSpaceUrl(String spaceID) {
        return SPACE + "/" + spaceID;
    }

    public static String getListsUrl(String spaceID) {
        return getSpaceUrl(spaceID) + LIST;
    }

    public static String getListUrl(String listID) {
        return LIST + "/" + listID;
    }

    public static String getTasksUrl(String listID) {
        return getListUrl(listID) + TASK;
    }

    public static String getTaskUrl(String taskID) {
        return TASK + "/" + taskID;
    }

}
