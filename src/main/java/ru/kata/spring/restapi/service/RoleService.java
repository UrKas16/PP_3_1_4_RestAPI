package ru.kata.spring.restapi.service;

import ru.kata.spring.restapi.model.Role;

import java.util.List;

public interface RoleService {

    void saveRole(Role role);

    Role getRoleByID(int id);

    List<Role> getAllRoles();

    void deleteRoleById(int id);

    List<Role> getRoleByName(List<String> name);
}
