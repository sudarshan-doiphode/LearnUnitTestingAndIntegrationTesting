package dev.darsh.mockito.data;

import dev.darsh.mockito.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserRepositoryTest {

    @Mock
    Map<Integer, User> userMap;

    @InjectMocks
    UserRepository userRepository;
    User user;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .id(1)
                .name("Sudarshan")
                .address("Pune")
                .build();

        userMap.put(user.getId(), user);
    }

    @Test
    @DisplayName(value = "Save user")
    void testSaveUser(){
        //Arrange
        when(userMap.containsKey(1)).thenReturn(false);
        when(userMap.put(1, user)).thenReturn(user);

        //Act & Assert
        assertTrue(userRepository.saveUser(user));
    }

    @Test
    @DisplayName(value = "User not saved due to existing user")
    void testSaveUserDoesNotSaveUserForExisting(){
        //Arrange
        when(userMap.containsKey(1)).thenReturn(true);

        //Act
        assertFalse(userRepository.saveUser(user));
    }

    @Test
    @DisplayName(value = "Get All users")
    void testGetAllUsers(){
        //Arrange
        List<User> users = new ArrayList<>();
        users.add(user);

        when(userMap.values()).thenReturn(users);

        // Act
        List<User> actual = userRepository.getAllUsers();

        //Assert
        assertIterableEquals(userMap.values(), actual);
    }
}