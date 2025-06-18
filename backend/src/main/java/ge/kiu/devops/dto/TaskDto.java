package ge.kiu.devops.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Task Data Transfer Object")
public class TaskDto {
    @Schema(description = "Task ID", example = "1")
    private Long id;
    @Schema(description = "Description of the task", example = "Buy groceries")
    private String description;
    @Schema(description = "Whether the task is completed or not", example = "false")
    private boolean completed;
}
