package dev.darsh.mockito.service;

import dev.darsh.mockito.model.User;

import java.util.List;

public interface UserService {

    User saveUser(int id, String username, String address);

    List<User> getUsers();
}
