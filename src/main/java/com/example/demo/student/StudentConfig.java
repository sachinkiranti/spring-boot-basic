package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student sachin = new Student(
                    "Sachin",
                    "sachinkiranti@gmail.com",
                    LocalDate.of(1993, Month.DECEMBER, 4)
            );

            Student uman = new Student(
                    "Uman",
                    "umanrai56@gmail.com",
                    LocalDate.of(1999, Month.SEPTEMBER, 18)
            );

            repository.saveAll(
                    List.of(sachin, uman)
            );
        };
    }

}
