package dev.darsh.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.darsh.entity.Student;
import dev.darsh.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = StudentController.class)
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    Student student;
    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        student = new Student();
        student.setId(1);
        student.setName("Sudarshan");
        student.setEmail("demo@example.com");

        objectMapper = new ObjectMapper();
    }


    @Test
    void testSaveStudent() throws Exception {
        //Arrange
        when(studentService.createStudent(Mockito.any(Student.class)))
                .thenReturn(student);

        var content = MockMvcRequestBuilders.post("/save")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(student));

        //Act
        String responseBodyAsString = mockMvc.perform(content)
                .andReturn()
                .getResponse()
                .getContentAsString();

        Student actual = objectMapper.readValue(responseBodyAsString, Student.class);

        //Assert
        assertEquals(student.getEmail(), actual.getEmail());
        assertEquals(student.getId(), actual.getId());
        assertEquals(student.getName(), actual.getName());
    }


}