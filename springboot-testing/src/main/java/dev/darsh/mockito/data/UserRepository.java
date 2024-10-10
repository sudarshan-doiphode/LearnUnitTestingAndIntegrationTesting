package dev.darsh.mockito.data;

import dev.darsh.mockito.model.User;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserRepository {

    public Map<Integer, User> userMap = new HashMap<>();

    public boolean saveUser(User user){
        if(!userMap.containsKey(user.getId())){
            User saved = userMap.put(user.getId(), user);
            return true;
        }
        return false;
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        // Efficiently retrieve all values using the `values()` method
        boolean result = users.addAll(userMap.values());
        return users;
    }
}
