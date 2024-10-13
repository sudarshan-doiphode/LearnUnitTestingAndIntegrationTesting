package dev.darsh.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.darsh.entity.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    TestRestTemplate testRestTemplate;
    Student student;

    ObjectMapper objectMapper;

    @BeforeEach
    void setUp(){
        student = new Student();
        student.setEmail("demo@gmail.com");
        student.setName("Sudarshan");

        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName(value = "Create user saves user in DB")
    void testSaveUser() throws JsonProcessingException {
        //Arrange
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(objectMapper.writeValueAsString(student), headers);

        //Act
        ResponseEntity<Student> studentResponseEntity = testRestTemplate.postForEntity("/save", entity, Student.class);
        Student response = studentResponseEntity.getBody();

        //Assert
        assertEquals(HttpStatus.OK.value(), studentResponseEntity.getStatusCode().value());
        assertEquals(student.getName(), response.getName());
    }

    @Test
    @DisplayName(value = "Get Student Returns student from DB")
    void testGetStudent(){
        //Act
        ResponseEntity<Student> studentResponseEntity = testRestTemplate.getForEntity("/user/2", Student.class);
        Student response = studentResponseEntity.getBody();

        //Assert
        assertEquals(HttpStatus.OK.value(), studentResponseEntity.getStatusCode().value());
        assertEquals(student.getName(), response.getName());
        assertEquals(student.getEmail(), response.getEmail());
    }

}