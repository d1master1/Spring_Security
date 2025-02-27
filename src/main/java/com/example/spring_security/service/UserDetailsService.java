package com.example.spring_security.service;

import org.apache.catalina.User;
import org.springframework.stereotype.Service;

@Service
public final class UserDetailsService implements UserDetailsService {

    private final UserService userService;

    public UserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUSername(String username) throws UsernameNotFondException {

        User user = userService.findUSer(username);

        if (user == null) {
            throw new UsernameNotFondException("Пользователя с таким именем не существует: " + username + "!");
        }
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.name())).toList()
        );
    }
}