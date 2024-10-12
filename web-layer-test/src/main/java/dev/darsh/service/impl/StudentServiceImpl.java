package dev.darsh.service.impl;

import dev.darsh.entity.Student;
import dev.darsh.repo.StudentRepository;
import dev.darsh.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student createStudent(Student student) {
        Optional<Student> optionalStudent = studentRepository.findByEmail(student.getEmail());
        if(optionalStudent.isEmpty()){
            Student saved = studentRepository.save(student);
            return saved;
        }
        return student;
    }
}
