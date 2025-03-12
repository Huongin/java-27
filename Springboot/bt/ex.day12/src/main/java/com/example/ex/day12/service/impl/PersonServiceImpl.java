package com.example.ex.day12.service.impl;

import com.example.ex.day12.model.Person;
import com.example.ex.day12.repository.PersonRepository;
import com.example.ex.day12.service.PersonService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    @Override
    public Person getPersonById(int id) {
        return personRepository.findById(id);
    }
}
