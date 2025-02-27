package com.example.spring_security.model;

public class UserDto {
    private int username;
    private String name;
    private String surname;
    private int password;

    public UserDto() {
    }

    public UserDto(int username, String name, String surname, int password) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.password = password;
    }

    public int getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getPassword() {
        return password;
    }
}