package ge.kiu.devops.service;

import ge.kiu.devops.dto.TaskCreateRequest;
import ge.kiu.devops.dto.TaskDto;
import ge.kiu.devops.entity.Task;
import ge.kiu.devops.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public List<TaskDto> getAllTasks() {
        return taskRepository.findAll().stream()
            .map(this::toDto)
            .toList();
    }

    public TaskDto getTaskById(Long id) {
        Task task = taskRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Task not found"));
        return toDto(task);
    }

    public TaskDto createTask(TaskCreateRequest request) {
        Task task = Task.builder()
            .description(request.getDescription())
            .completed(request.isCompleted())
            .build();
        Task saved = taskRepository.save(task);
        return toDto(saved);
    }

    public TaskDto updateTask(Long id, TaskCreateRequest request) {
        Task task = taskRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Task not found"));
        task.setDescription(request.getDescription());
        task.setCompleted(request.isCompleted());
        Task saved = taskRepository.save(task);
        return toDto(saved);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    private TaskDto toDto(Task task) {
        return TaskDto.builder()
            .id(task.getId())
            .description(task.getDescription())
            .completed(task.isCompleted())
            .build();
    }
}
