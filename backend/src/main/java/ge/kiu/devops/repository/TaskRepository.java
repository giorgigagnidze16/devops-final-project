package ge.kiu.devops.repository;


import ge.kiu.devops.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
