package ru.kata.spring.boot_security.demo.dao;

import java.util.List;

import ru.kata.spring.boot_security.demo.model.User;


public interface UserDao {
    void saveUser(User user);
    User getUserById(long id);

    void updateUser(User user);

    List<User> getAllUsers();

    void deleteUserById(long id);

    User getUserByUsername(String username);
}
