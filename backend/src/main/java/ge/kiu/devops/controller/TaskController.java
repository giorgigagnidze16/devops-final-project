package ge.kiu.devops.controller;

import ge.kiu.devops.dto.TaskCreateRequest;
import ge.kiu.devops.dto.TaskDto;
import ge.kiu.devops.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@Tag(name = "Tasks", description = "Task management API")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping
    @Operation(summary = "Get all tasks", description = "Retrieve a list of all tasks")
    public List<TaskDto> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a task by ID", description = "Retrieve a single task by its ID")
    public TaskDto getTask(@Parameter(description = "ID of the task", example = "1") @PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @PostMapping
    @Operation(summary = "Create a new task", description = "Create a new task with the provided details")
    public TaskDto createTask(@RequestBody TaskCreateRequest request) {
        return taskService.createTask(request);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing task", description = "Update the task with the specified ID")
    public TaskDto updateTask(
        @Parameter(description = "ID of the task to update", example = "1")
        @PathVariable Long id,
        @RequestBody TaskCreateRequest request) {
        return taskService.updateTask(id, request);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a task", description = "Delete the task with the specified ID")
    public void deleteTask(
        @Parameter(description = "ID of the task to delete", example = "1")
        @PathVariable Long id) {
        taskService.deleteTask(id);
    }
}
