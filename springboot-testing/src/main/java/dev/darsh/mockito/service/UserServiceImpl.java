package dev.darsh.mockito.service;

import dev.darsh.mockito.data.UserRepository;
import dev.darsh.mockito.exception.UserNotSavedException;
import dev.darsh.mockito.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public User saveUser(int id, String username, String address){
        User user = new User();
        if(id>0){
            user.setId(1);
        }
        if(!username.isBlank()){
            user.setName(username);
        }
        if(!address.isBlank()){
            user.setAddress(address);
        }

        boolean isSaved = userRepository.saveUser(user);
        if(!isSaved) throw new UserNotSavedException("User is not saved in DB 😔");

        return user;
    }

    @Override
    public List<User> getUsers() {
        return userRepository.getAllUsers();
    }
}
