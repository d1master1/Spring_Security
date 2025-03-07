package com.example.spring_security.repo;

import com.example.spring_security.dto.UserDto;
import com.example.spring_security.model.User;
import java.util.List;

public interface UserService {

    void save(UserDto userdto);
    User findUser(String username);


    List<User> findAllUsers();

    boolean isUsernameAvailable(String username);
}