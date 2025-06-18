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
@Schema(description = "Create or update a Task request")
public class TaskCreateRequest {
    @Schema(description = "Description of the task", example = "Buy products")
    private String description;
    @Schema(description = "Whether the task is completed", example = "false")
    private boolean completed;
}
