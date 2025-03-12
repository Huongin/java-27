package com.example.ex.day12.service;

import com.example.ex.day12.model.Person;

import java.util.List;

public interface PersonService {
    List<Person> getAllPersons();

    Person getPersonById(int id);
}
