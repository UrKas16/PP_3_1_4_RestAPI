package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.UserRepoitory;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepoitory userRepoitory;

    private final PasswordEncoder passwordEncoder;


    @Autowired
    public UserServiceImpl(UserRepoitory userRepoitory, PasswordEncoder passwordEncoder) {

        this.userRepoitory = userRepoitory;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void saveAndUpdateUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepoitory.save(user);
    }


    @Override
    public User getUserById(long id) {
        User user = null;
        Optional<User> optionalUser = userRepoitory.findById(id);

        if (optionalUser.isPresent()){
            user = optionalUser.get();
        }
        return user;
    }


    @Override
    public void deleteUserById(long id) {
        userRepoitory.deleteById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepoitory.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepoitory.findUserByUserAlias(username);
    }
}
