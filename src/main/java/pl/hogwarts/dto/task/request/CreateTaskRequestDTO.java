package pl.hogwarts.dto.task.request;

import lombok.Data;

@Data //gettery, settery nadpisanie metod toString
public class CreateTaskRequestDTO {

    private String name;
    private String description;
    private String status;
    private String priority;
    private String parent;
    private String timeEstimate;
    private String assignees;
    private boolean archived;

}
