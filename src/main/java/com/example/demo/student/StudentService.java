package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> get() {
        return studentRepository.findAll();
    }

    public void create(Student student) {
        Optional<Student> studentByEmail = studentRepository
                .findByEmail(student.getEmail());

        if (studentByEmail.isPresent()) {
            throw new IllegalStateException("Email is already taken!");
        }

//        System.out.println(student);
        studentRepository.save(student);
    }

    public void destroy(Long id) {
        boolean exists = studentRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Student with id " + id + " doesn't exists!");
        }
        studentRepository.deleteById(id);
    }

    @Transactional
    public void update(Long id, String name, String email) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "Student with id " + id + " doesn't exits !"
                ));

        if (name != null &&
            name.length() > 0 &&
                !Objects.equals(student.getName(), name)
        ) {
            student.setName(name);
        }

        if (email != null &&
                email.length() > 0 &&
                !Objects.equals(student.getEmail(), email)
        ) {
            Optional<Student> emailExists = studentRepository.findByEmail(email);

            if (emailExists.isPresent()) {
                throw new IllegalStateException("Email is already taken!");
            }
            student.setEmail(email);
        }
    }
}
