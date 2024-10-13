package dev.darsh.entity;

import jakarta.persistence.PersistenceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class StudentEntityIntegrationTest {

    @Autowired
    private TestEntityManager testEntityManager;
    Student student;

    @BeforeEach
    void setUp(){
        student = new Student();
        student.setEmail("xyz@gmail.com");
    }

    /*
    * By default, Spring rolls back the transaction after each test method in @DataJpaTest.
    * This is done to ensure each test starts with a clean state. Even though persistAndFlush()
    * saves the entity in the database during the test execution, it is rolled back after the test completes.
    * */
    @Test
    void testSaveStudent_WhenValidDetailsProvided(){
        //Arrange
        student.setName("Neha");

        //Act
        Student persisted = testEntityManager.persistAndFlush(student);

        //Assert
        assertTrue(persisted.getId()>0);
        assertEquals(student.getEmail(), persisted.getEmail());
    }

    @Test
    void testSaveStudent_WhenInvalidDetailsProvided(){
        //Arrange
        student.setName("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");

        //Act and Assert
        assertThrows(PersistenceException.class, () -> {
            testEntityManager.persistAndFlush(student);
        });
    }

}