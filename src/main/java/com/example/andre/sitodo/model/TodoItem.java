package com.example.andre.sitodo.model;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class TodoItem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Getter
    @Setter
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column
    private Boolean finished = Boolean.FALSE;

    public TodoItem(String title) {
        this.title = title;
    }


    public TodoItem(Long id, String title) {
        this.id = id;
        this.title = title;
    }
}
