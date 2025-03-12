package com.example.ex.day12.controller;

import com.example.ex.day12.model.Person;
import com.example.ex.day12.service.PersonService;
import org.apache.logging.log4j.util.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


import java.text.Collator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Controller

public class WedController {
    public WedController(PersonService personService) {
        this.personService = personService;
    }

    private final PersonService personService;

    //Lấy danh sách people
    @GetMapping("/") //http://localhost:8080
    public String getAllPersons(Model model,
                               @RequestParam(required = false) String keyword) {

        List<Person> persons = personService.getAllPersons();
        List<Person> personFound = new ArrayList<>();
        if(keyword != null){
            personFound = persons.stream()
                    .filter((p -> p.getFullname().toLowerCase().contains(keyword.toLowerCase())))
                    .toList();
        }else {
            personFound = persons;
        }
        model.addAttribute("persons", persons);
        model.addAttribute("persons", personFound);
        return "personList";
    }

    @GetMapping("/person/{id}")
    public String getPersonById(Model model, @PathVariable int id){
        Person person = personService.getPersonById(id);

        //Lọc người liên quan cùng giới tính, khác id, giới hạn 4 người
        List<Person> relatedPersons = personService.getAllPersons().stream()
                .filter(p -> p.getGender().equals(person.getGender()) && p.getId() != person.getId())
                .sorted(Comparator.comparing(Person::getId).reversed())
                .limit(4)
                .collect(Collectors.toList());


        model.addAttribute("person", person);
        model.addAttribute("relatedPersons", relatedPersons);
        return "person-detail";

    }

}

