package br.com.dev.todosimple.todosimple.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@Entity
@Table(name = Task.TABLE_NAME)
public class Task extends AbstractEntity {
    public static final String TABLE_NAME = "task";

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private User user;
    @Column(name = "description", length = 255, nullable = false)
    @NotNull
    @NotEmpty
    @Size(min = 1, max = 255)
    private String description;

    public Task(User user, String description) {
        this.user = user;
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Task task = (Task) o;
        return Objects.equals(user, task.user) && Objects.equals(description, task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), user, description);
    }
}
