package br.com.dev.todosimple.todosimple.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@MappedSuperclass
@Data
public class AbstractEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private long id;
}
