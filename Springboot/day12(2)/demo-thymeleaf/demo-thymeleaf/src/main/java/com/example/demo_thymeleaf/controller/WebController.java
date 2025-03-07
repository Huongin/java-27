package com.example.demo_thymeleaf.controller;


import com.example.demo_thymeleaf.model.PageRespone;
import com.example.demo_thymeleaf.model.Person;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


@Controller
public class WebController {
    private List<Person> people = new ArrayList<>();

    public WebController() {
        Faker faker = new Faker();
        Random rd = new Random();
        for (int i = 0; i < 55; i++) {
            Person person = new Person(
                    i + 1,
                    faker.name().fullName(),
                    rd.nextInt(2) == 1 ? "M" : "F",
                    faker.number().numberBetween(18, 60)
            );
            people.add(person);
        }
    }

//    // http://localhost:8080
//    // http://localhost:8080?keyword=Nguyen&page=1
    @GetMapping("/")
    public String getHome (Model model,
                          @RequestParam(required = false) String keyword,
                          @RequestParam(required = false,defaultValue = "1") int page){


        List<Person> peopleFound = new ArrayList<>();
        if (keyword != null) {
            peopleFound = people.stream()
                    .filter(p -> p.getName().toLowerCase().contains(keyword.toLowerCase()))
                    .toList();
        } else {
            peopleFound = people;
        }

        PageRespone<Person> pageRespone = new PageRespone<>(peopleFound, 10, page);
        model.addAttribute("pageResponse", pageRespone);
        model.addAttribute("people", peopleFound);
        model.addAttribute("person", people.get(0));

        model.addAttribute("name", "Nguyen Van A");
        model.addAttribute("salary", 1000);
        return "index";
    }

    @GetMapping("/person/{id}")//http://localhost:8080/person/1
    public String getPerson(Model model, @PathVariable int id) {
        Person person = people.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);

        if (person == null) {
            return "error"; // Trả về trang lỗi nếu không tìm thấy person
        }

        // Lấy danh sách person liên quan (cùng gender, không trùng ID, sắp xếp giảm dần, giới hạn 4 người)
        List<Person> relatedPersons = people.stream()
                .filter(p -> p.getGender().equals(person.getGender()) && p.getId() != person.getId()) // Cùng giới tính, khác ID
                .sorted(Comparator.comparing(Person::getId).reversed()) // Sắp xếp giảm dần theo ID
                .limit(4) // Giới hạn 4 người
                .collect(Collectors.toList());

        // Debug: In ra console để kiểm tra
        System.out.println("Person hiện tại: " + person.getName() + " - ID: " + person.getId() + " - Giới tính: " + person.getGender());
        System.out.println("Danh sách người liên quan:");
        if (relatedPersons.isEmpty()) {
            System.out.println("Không có người liên quan.");
        } else {
            relatedPersons.forEach(p -> System.out.println(p.getName() + " - ID: " + p.getId() + " - Giới tính: " + p.getGender()));
        }

        // Đưa dữ liệu vào model
        model.addAttribute("person", person);
        model.addAttribute("relatedPeople", relatedPersons);

        return "person";
    }
}
