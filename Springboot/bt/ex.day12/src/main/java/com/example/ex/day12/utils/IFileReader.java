package com.example.ex.day12.utils;

import com.example.ex.day12.model.Person;

import java.util.List;

public interface IFileReader {
    List<Person> readFile(String filePath);
}
