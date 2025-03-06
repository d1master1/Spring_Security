package com.example.spring_security.service;

import com.example.spring_security.dto.UserDto;
import com.example.spring_security.model.Role;
import com.example.spring_security.repo.UserRepo;
import com.example.spring_security.repo.UserService;
import com.example.spring_security.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;

@Service
public class AppUserService implements UserService {

    private final UserRepo userRepo;
    private final BCryptPasswordEncoder encoder;

    public AppUserService(UserRepo userRepo, BCryptPasswordEncoder encoder) {
        this.userRepo = userRepo;
        this.encoder = encoder;
    }

    @Override
    public void save(UserDto userdto) {
        User user = new User();
        user.setUsername(userdto.getUsername());
        user.setPassword(encoder.encode(userdto.getPassword()));
        user.setName(userdto.getName() + " " + userdto.getSurname());
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);
    }

    @Override
    public User findUser(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepo.findAll();
    }


    @Override
    public boolean isUsernameAvailable(String username) {
        return userRepo.existsByUsername(username);
    }
}