package dev.darsh.mockito.controller;

import dev.darsh.mockito.exception.UserNotSavedException;
import dev.darsh.mockito.model.User;
import dev.darsh.mockito.service.UserService;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    UserService userService;

    @InjectMocks
    UserController controller;

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
    @DisplayName("Save user into local repository")
    void testSaveUser() {
        // Arrange
        when(userService.saveUser(Mockito.anyInt(), Mockito.anyString(), Mockito.anyString()))
                .thenReturn(user);

        //Act
        User actual = controller.saveUser(User.builder().id(1).name("Sudarshan").address("Pune").build()).getBody();

        //Assert
        assertEquals(user, actual);
        assertNotNull(actual);
        assertSame("Sudarshan", actual.getName());
    }

    //    Use doThrow for void methods else use assertThrows
    @Test
    void testSaveUserThrowsException() {
        // Arrange
        when(userService.saveUser(Mockito.anyInt(), Mockito.anyString(), Mockito.anyString()))
                .thenThrow(UserNotSavedException.class);

        //Act and assert
        assertThrows(UserNotSavedException.class, () -> controller.saveUser(user));
    }


    @Test
    @DisplayName(value = "Get all saved users")
    void testGetAllUsers() {
        //Arrange
        when(userService.getUsers()).thenReturn(users);

        //Act
        List<User> actual = controller.getUsers().getBody();

        //Assert
        assertIterableEquals(users, actual);
    }
}