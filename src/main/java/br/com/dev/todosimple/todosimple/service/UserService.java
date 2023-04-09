package br.com.dev.todosimple.todosimple.service;

import br.com.dev.todosimple.todosimple.model.User;
import br.com.dev.todosimple.todosimple.repository.TaskRepository;
import br.com.dev.todosimple.todosimple.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TaskRepository taskRepository;

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "User not found! ID:" + id + ", Type:" + User.class.getName()
                ));
    }

    @Transactional
    public User save(User user) {
        user.setId(null);
        user = this.userRepository.save(user);
        this.taskRepository.saveAll(user.getTasks());
        return user;
    }

    @Transactional
    public User update(User user) {
        User newUser = findById(user.getId());
        user.setPassword(user.getPassword());
        return this.userRepository.save(newUser);
    }

    public void delete(Long id) {
        findById(id);
        try {
            this.userRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Cannot delete the entity in application");
        }
    }

}
