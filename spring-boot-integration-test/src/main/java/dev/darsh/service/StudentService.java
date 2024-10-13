package dev.darsh.service;

import dev.darsh.entity.Student;
import org.springframework.http.HttpStatusCode;

public interface StudentService {

    Student createStudent(Student student);

    Student getStudent(int id);
}
