package com.example.demo.repo;

import com.example.demo.models.Human;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HumanRepository extends CrudRepository<Human, Long> {

    List<Human> findByLastNameContains(String lastName);
    List<Human> findByLastNameIsContaining(String lastName);
    List<Human> findByLastNameContaining(String lastName);
    List<Human> findByLastNameEquals(String lastName);
    //List<Human> findByWeightContains(Double weight);

}
