package com.example.demo.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// This repo is responsible for data access

@Repository
public interface StudentRepository
        extends JpaRepository<Student, Long> {

    @Query("SELECT s from Student s WHERE s.email = ?1")
    Optional<Student> findByEmail(String email);

}
