package dev.darsh.controller;

import dev.darsh.entity.Student;
import dev.darsh.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/save")
    public ResponseEntity<Student> saveStudent(@RequestBody @Valid Student student){
        Student s1 = studentService.createStudent(student);
        return ResponseEntity.ok(s1);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") int id){
        Student student = studentService.getStudent(id);
        return ResponseEntity.status(200)
                .body(student);
    }
}
