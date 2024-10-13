package dev.darsh.repo;

import dev.darsh.entity.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
// AutoConfigureTestDatabase add this as @DataJPATest replace datasource with In-Memory DB.
// If you are interested in testing with actual DB do this changes.
// But for testing always prefer In-Memory DB
class StudentRepositoryTest {

    @Autowired
    StudentRepository studentRepository;

    Student student;
    List<Student> studentList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        student = new Student();
        student.setEmail("sudarshan@gmail.com");
        student.setName("Sudarshan");

        Student s1 = new Student(3, "example@outlook.com", "example");
        Student s2 = new Student(4, "neha@outlook.com", "neha");
        studentList.addAll(List.of(s1, s2));
    }

    @Test
    @DisplayName(value = "Return Student when correct email provided")
    void testFindByEmailWorksFine() {
        //Act
        Optional<Student> optionalStudent = studentRepository.findByEmail(student.getEmail());

        //Assert
        assertEquals(student.getEmail(), optionalStudent.get().getEmail(), "Expected email does not match with stored entity record");
    }

    @Test
    @DisplayName(value = "Return List of students for email ends with specific suffix")
    void testFindByEmailEndsWith(){
        //Arrange

        String suffix = "outlook.com";

        //Act
        List<Student> actualStudentList = studentRepository.findByEmailEndsWith(suffix);

        assertEquals(studentList.size(), actualStudentList.size());
        for (int i = 0; i < studentList.size(); i++) {
            assertEquals(studentList.get(i).getEmail(), actualStudentList.get(i).getEmail());
            assertEquals(studentList.get(i).getName(), actualStudentList.get(i).getName());
        }

    }
}