package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.User;


import java.util.List;

public interface UserService extends UserDetailsService {
    void saveAndUpdateUser(User user);
    User getUserById(long id);
    void deleteUserById(long id);
    List<User> getAllUsers();
    UserDetails loadUserByUsername(String username);

}
