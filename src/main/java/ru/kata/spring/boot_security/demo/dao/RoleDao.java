package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

public interface RoleDao {

    void saveRole(Role role);

    Role getRoleByID(int id);

    List<Role> getAllRoles();

    void deleteRoleById(int id);


}
