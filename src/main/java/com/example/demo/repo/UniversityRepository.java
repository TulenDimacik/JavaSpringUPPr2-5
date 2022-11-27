package com.example.demo.repo;


import com.example.demo.models.University;
import org.springframework.data.repository.CrudRepository;

public interface UniversityRepository extends CrudRepository<University, Long> {
    University findByName(String name);
}
