package br.com.dev.todosimple.todosimple.repository;

import br.com.dev.todosimple.todosimple.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String name);

}
