package com.example.demo.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class University {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column
    private String name;
    @ManyToMany
    @JoinTable(name="human_university",
            joinColumns=@JoinColumn(name="university_id"),
            inverseJoinColumns=@JoinColumn(name="human_id"))
    private List<Human> students;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Human> getStudents() {
        return students;
    }

    public void setStudents(List<Human> students) {
        this.students = students;
    }

}
