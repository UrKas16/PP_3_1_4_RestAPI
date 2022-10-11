package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService{

    private final RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Transactional
    @Override
    public void saveRole(Role role) {
        roleDao.saveRole(role);
    }

    @Override
    public Role getRoleByID(int id) {
        return roleDao.getRoleByID(id);
    }

    @Override
    public List<Role> getRoleByName(String name) {
        List<Role> roles = new ArrayList<>();
        for (Role role : getAllRoles()) {
            if (name.contains(role.getNameRole()))
                roles.add(role);
        }
        return roles;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleDao.getAllRoles();
    }

    @Transactional
    @Override
    public void deleteRoleById(int id) {
        roleDao.deleteRoleById(id);
    }
}
