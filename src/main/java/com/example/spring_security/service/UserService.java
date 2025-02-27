package com.example.spring_security.service;

import com.example.spring_security.model.UserDto;
import org.apache.catalina.User;

public interface UserService {
    void save(UserDto userDto);
    User findUSer(String username);
}
