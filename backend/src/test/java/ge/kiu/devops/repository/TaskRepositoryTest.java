package ge.kiu.devops.repository;

import ge.kiu.devops.entity.Task;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class TaskRepositoryTest {
    @Autowired
    private TaskRepository taskRepository;

    @Test
    @DisplayName("Should save and retrieve a Task")
    void testSaveAndFindTask() {
        Task task = Task.builder().description("Test Task").completed(false).build();
        task = taskRepository.save(task);

        List<Task> tasks = taskRepository.findAll();
        assertThat(tasks).hasSize(1);
        assertThat(tasks.getFirst().getDescription()).isEqualTo("Test Task");
        assertThat(tasks.getFirst().isCompleted()).isFalse();
    }
}
