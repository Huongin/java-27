package com.example.ex.day12.repository;

import com.example.ex.day12.model.Person;

import java.util.List;

public interface PersonRepository {
    List<Person> findAll();

    Person findById(int id);
}
