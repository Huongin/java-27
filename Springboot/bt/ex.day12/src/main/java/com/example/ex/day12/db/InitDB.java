package com.example.ex.day12.db;

import com.example.ex.day12.model.Person;
import com.example.ex.day12.utils.IFileReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.List;



@Slf4j
@Configuration
public class InitDB implements CommandLineRunner {

    private final IFileReader FileReader;

    public InitDB(@Qualifier("csvFileReader") IFileReader fileReader) {
        FileReader = fileReader;
    }


    @Override
    public void run(String... args) throws Exception {
        log.info("Start init data...");

        List<Person> personCsv = FileReader.readFile("src/main/resources/person.csv");
        log.info("Person from Csv size: {}", personCsv.size());

        PersonDB.persons = personCsv;
    }
}
