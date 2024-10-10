package dev.darsh.mockito.service;

import dev.darsh.mockito.data.UserRepository;
import dev.darsh.mockito.exception.UserNotSavedException;
import dev.darsh.mockito.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    UserRepository repository;

    @InjectMocks
    UserServiceImpl userService;

    User user;
    List<User> users = new ArrayList<>();

    @BeforeEach
    void setUp() {
        user = User.builder()
                .id(1)
                .name("Sudarshan")
                .address("Pune")
                .build();

        users.add(user);
    }

    @Test
    @DisplayName(value = "Save user")
    void testSaveUser() {
        // Arrange
        when(repository.saveUser(Mockito.any(User.class))).thenReturn(true);

        // Act
        User actual = userService.saveUser(1, "Sudarshan", "Pune");

        // Assert
        assertNotNull(actual);
        assertEquals(user.getId(), actual.getId());
        assertEquals(user.getName(), actual.getName());
        assertEquals(user.getAddress(), actual.getAddress());
    }

    @Test
    @DisplayName(value = "Save user throws exception")
    void testSaveUserThrowsException(){
        //Arrange
        when(repository.saveUser(Mockito.any(User.class)))
                .thenReturn(false);

        assertThrows(UserNotSavedException.class, () -> userService.saveUser(-1, "",""));
    }

    @Test
    @DisplayName(value = "Get all users")
    void testGetUsers(){
        //Arrange
        when(repository.getAllUsers()).thenReturn(users);

        //Act and Assert
        List<User> actualList = userService.getUsers();
        assertIterableEquals(users, actualList);
    }

}