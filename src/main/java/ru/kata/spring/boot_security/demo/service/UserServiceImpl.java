package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.UserDao;


import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao dao) {
        this.userDao = dao;
    }

    @Transactional
    @Override
    public void saveUser(User user) {
        userDao.saveUser(user);
    }
    @Override
    public User getUserById(long id) {
        return this.userDao.getUserById(id);
    }
    @Transactional
    @Override
    public void updateUser(User user) {
        this.userDao.updateUser(user);
    }
    @Transactional
    @Override
    public void deleteUserById(long id) {
        this.userDao.deleteUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return this.userDao.getAllUsers();
    }

}
