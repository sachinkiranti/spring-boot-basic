package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> get() {
        return studentService.get();
    }

    @PostMapping(path = "/create")
    public void create(@RequestBody Student student) {
        studentService.create(student);
    }

    @DeleteMapping(path = "{studentId}/delete")
    public void delete(@PathVariable("studentId") Long id) {
        studentService.destroy(id);
    }

    @PutMapping(path = "{studentId}/update")
    public void update(
            @PathVariable("studentId") Long id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email
    ) {
        studentService.update(id, name, email);
    }

}
