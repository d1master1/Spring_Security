package com.example.spring_security.service;

import com.example.spring_security.model.UserDto;
import com.example.spring_security.repo.UserRepo;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AppUserService implements  com.example.spring_security.service.UserService{

    private final UserRepo userRepo;
    private final BCryptPasswordEncoder encoder;

    public AppUserService(UserRepo userRepo, BCryptPasswordEncoder encoder){
        this.userRepo = userRepo;
        this.encoder = encoder;
    }

    @Override
    public void save(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(encoder.encode(userDto.getPassword()));
        user.setName(userDto.getName() + " " + userDto.getSurname());
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);
    }
    @Override
    public User findUSer(String username) { return userRepo.findByUsername(username); }

}