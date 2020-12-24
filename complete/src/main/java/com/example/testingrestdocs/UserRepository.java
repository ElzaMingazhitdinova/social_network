package com.example.testingrestdocs;


import com.example.testingrestdocs.objects.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserRepository {
    private Map<Long, User> userMap;
    private Map<String, Long> nameIndexMap;

    public UserRepository() {
        this.userMap = new HashMap<>();
        this.nameIndexMap = new HashMap<>();
    }

    public void createUser(User user) {
        userMap.put(user.getId(), user);
        nameIndexMap.put(user.getUsername(), user.getId());
    }

    public User findUser(String userName) {
        return userMap.get(nameIndexMap.get(userName));
    }
}
