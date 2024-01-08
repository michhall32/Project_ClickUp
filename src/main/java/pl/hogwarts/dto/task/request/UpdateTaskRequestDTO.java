package pl.hogwarts.dto.task.request;

import lombok.Data;

//not used -> with PUT it's better to use JSONObject instead of POJO because notsent fields stay the same
@Data
public class UpdateTaskRequestDTO {

    private String name;
    private String description;
    private String status;
    private String priority;
    private String parent;
    private String timeEstimate;
    private String assignees;
    private boolean archived;
}
