package com.example.ex.day12.repository.Impl;

import com.example.ex.day12.db.PersonDB;
import com.example.ex.day12.model.Person;
import com.example.ex.day12.repository.PersonRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonRepositoryImpl implements PersonRepository {

    @Override
    public List<Person> findAll() {
        return PersonDB.persons;
    }

    @Override
    public Person findById(int id) {
        return PersonDB.persons.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
