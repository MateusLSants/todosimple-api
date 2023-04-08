package br.com.dev.todosimple.todosimple.repository;

import br.com.dev.todosimple.todosimple.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Task> findById(Long id);
    List<Task> findByUser_id(Long id);
}
