package com.example.baitap_thymeleaf.model;


public class Person {
    private int id;
    private String fullname;
    private String city;
    private String job;
    private int salary;

    public Person() {
    }

    public Person(int id, String fullname, String city, String job, int salary) {
        this.id = id;
        this.fullname = fullname;
        this.city = city;
        this.job = job;
        this.salary = salary;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}