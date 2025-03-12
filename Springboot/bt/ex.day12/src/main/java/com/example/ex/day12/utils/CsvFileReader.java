package com.example.ex.day12.utils;

import com.example.ex.day12.model.Person;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.example.ex.day12.db.PersonDB.persons;

@Slf4j
@Component("csvFileReader")

public class CsvFileReader implements IFileReader{

    @Override
    public List<Person> readFile(String filePath) {
        log.info("Csv file reading...");

        try(final CSVReader csvReader = new CSVReader(new FileReader(filePath))) {
            csvReader.skip(1);

            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {
                log.info("Reading row: {}", Arrays.toString(nextRecord));
                if (nextRecord.length >= 7) {
                    int id = Integer.parseInt(nextRecord[0].trim());
                    String fullname = nextRecord[1].trim();
                    String job = nextRecord[2].trim();
                    String gender = nextRecord[3].trim();
                    String city = nextRecord[4].trim();
                    double salary = Double.parseDouble(nextRecord[5].replace("¥", "").trim());
                    Date birthday = new SimpleDateFormat("M/d/yyyy").parse(nextRecord[6].trim());

                    persons.add(new Person(id, fullname, job, gender, city, salary, birthday));
                }
            }
        }catch (IOException e){
            log.error("Error reading CSV file: {}", filePath, e);
        } catch (CsvValidationException | ParseException e) {
            throw new RuntimeException(e);
        }
        log.info("Total persons read: {}", persons.size());
        return persons;
    }
}
