package com.example.baitap_thymeleaf.controller;


import com.example.baitap_thymeleaf.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class WedController {
    private List<Person> people = new ArrayList<>(List.of(
            new Person(1, "Nguyễn Văn A", "Hải Phòng", "Lái xe", 1200),
            new Person(2, "Lưu Thị B", "Hà Nội", "Giáo viên", 800),
            new Person(3, "Trần Văn C", "Hồ Chí Minh", "Kỹ sư", 1500),
            new Person(4, "Mai Thị D", "Đà Nẵng", "Bác sĩ", 2000),
            new Person(5, "Hoàng Văn E", "Hà Tĩnh", "Công nhân", 1800),
            new Person(6, "Trịnh Thị F", "Quãng Ngãi", "Giáo viên", 900)
    ));

    @GetMapping("/")
    public String getHome(Model model) {
        List<String> menu = Arrays.asList(
                "Danh sách người theo thành phố",
                "Danh sách nghề nghiệp và số người làm nghề",
                "Những người có lương cao hơn mức trung bình",
                "Người có tên dài nhất"
        );

        model.addAttribute("menu", menu);
        return "index";
    }

    //Tìm kiếm nhóm người theo thành phố
    @GetMapping("/groupPeopleByCity")
    public String groupPeopleByCity(Model model) {
        Map<String, List<Person>> peopleByCity = people.stream()
                .collect(Collectors.groupingBy(Person::getCity));
        model.addAttribute("peopleByCity", peopleByCity);
        return "groupPeopleByCity";
    }

   // Tìm kiếm danh sách nghề nghiệp và số lượng người
    @GetMapping("/groupJobByCount")
    public String groupJobByCount(Model model) {
        Map<String, Long> jobCount = people.stream()
                .collect(Collectors.groupingBy(Person::getJob, Collectors.counting()));
       model.addAttribute("jobCount", jobCount);
        return "groupJobByCount";
    }

    // Tìm kiếm danh sách người có mức lương lớn hơn mức trung bình
    @GetMapping("/aboveAverageSalary")
    public String aboveAverageSalary(Model model) {
        double averageSalary = people.stream()
                .mapToDouble(Person::getSalary)
                .average()
                .orElse(0);

        List<Person> aboveAverage = people.stream()
                .filter(p -> p.getSalary() > averageSalary)
                .collect(Collectors.toList());

        model.addAttribute("aboveAveragePeople", aboveAverage);
        model.addAttribute("averageSalary", averageSalary);
        return "aboveAverageSalary";
    }

    // Tìm kiếm người có tên dài nhất
    @GetMapping("/longestName")
    public String longestName(Model model) {
        Person longestNamePerson = people.stream()
                .max(Comparator.comparingInt(p -> p.getFullname().length()))
                .orElse(null);

        model.addAttribute("longestNamePerson", longestNamePerson);
        return "longestName";
    }
}


