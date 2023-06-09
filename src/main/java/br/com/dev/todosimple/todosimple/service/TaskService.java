package br.com.dev.todosimple.todosimple.service;

import br.com.dev.todosimple.todosimple.model.Task;
import br.com.dev.todosimple.todosimple.model.User;
import br.com.dev.todosimple.todosimple.repository.TaskRepository;
import br.com.dev.todosimple.todosimple.service.exceptions.DataBidingViolationException;
import br.com.dev.todosimple.todosimple.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserService userService;

    public List<Task> findAllTask() {
        return taskRepository.findAll();
    }

    public Task findById(long id) {
        Optional<Task> task = this.taskRepository.findById(id);
        return task.orElseThrow(() -> new ObjectNotFoundException(
                "Task not found! ID: " + id + ", Type: " + Task.class.getName()
        ));
    }

    @Transactional
    public Task save(Task task) {
        User user = this.userService.findById(task.getUser().getId());
        task.setUser(user);
        task = this.taskRepository.save(task);
        return task;
    }

    @Transactional
    public Task update(Task task) {
        Task newTask = findById(task.getId());
        newTask.setDescription(task.getDescription());
        return this.taskRepository.save(newTask);
    }

    public void delete(long id) {
        findById(id);
        try {
            this.taskRepository.deleteById(id);
        } catch (Exception e) {
            throw new DataBidingViolationException("Cannot delete the entity in application");
        }
    }

    public  List<Task> findByUser(Long userId) {
        List<Task> tasks = this.taskRepository.findByUser_id(userId);
        return tasks;
    }
}
