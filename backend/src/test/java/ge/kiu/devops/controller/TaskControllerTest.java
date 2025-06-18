package ge.kiu.devops.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ge.kiu.devops.entity.Task;
import ge.kiu.devops.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TaskControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        taskRepository.deleteAll();
    }

    @Test
    @DisplayName("Create Task")
    void testCreateTask() throws Exception {
        Task task = Task.builder().description("Integration Test").completed(false).build();

        mockMvc.perform(post("/api/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(task)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.description", is("Integration Test")))
            .andExpect(jsonPath("$.completed", is(false)));
    }

    @Test
    @DisplayName("Get All Tasks")
    void testGetAllTasks() throws Exception {
        taskRepository.save(Task.builder().description("Task 1").completed(false).build());
        taskRepository.save(Task.builder().description("Task 2").completed(true).build());

        mockMvc.perform(get("/api/tasks"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    @DisplayName("Update Task")
    void testUpdateTask() throws Exception {
        Task task = taskRepository.save(Task.builder().description("To Update").completed(false).build());

        Task updated = Task.builder().description("Updated").completed(true).build();

        mockMvc.perform(put("/api/tasks/{id}", task.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updated)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.description", is("Updated")))
            .andExpect(jsonPath("$.completed", is(true)));
    }

    @Test
    @DisplayName("Delete Task")
    void testDeleteTask() throws Exception {
        Task task = taskRepository.save(Task.builder().description("To Delete").completed(false).build());

        mockMvc.perform(delete("/api/tasks/{id}", task.getId()))
            .andExpect(status().isOk());

        assertTrue(taskRepository.findById(task.getId()).isEmpty());
    }
}
