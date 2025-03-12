package com.example.ex.day12.model;

import lombok.*;

import java.util.Date;
 @Getter
 @Setter
 @ToString
 @NoArgsConstructor
 @AllArgsConstructor
 @EqualsAndHashCode
 @Builder
public class Person {
        private int id;
        private String fullname;
        private String job;
        private String gender;
        private String city;
        private double salary;
        private Date birthday;

}
