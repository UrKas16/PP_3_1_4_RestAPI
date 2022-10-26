package ru.kata.spring.restapi.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.restapi.model.Role;
import ru.kata.spring.restapi.repository.RoleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {

        this.roleRepository = roleRepository;
    }

    @Transactional
    @Override
    public void saveRole(Role role) {

        roleRepository.save(role);
    }

    @Override
    public Role getRoleByID(int id) {
        Role role = null;
        Optional<Role> optionalRole = roleRepository.findById(id);

        if (optionalRole.isPresent()) {
            role = optionalRole.get();
        }
        return role;
    }

    @Override
    public List<Role> getRoleByName(List<String> name) {
        List<Role> roles = new ArrayList<>();
        for (Role role : getAllRoles()) {
            if (name.contains(role.getNameRole()))
                roles.add(role);
        }
        return roles;
    }

    @Override
    public List<Role> getAllRoles() {

        return roleRepository.findAll();
    }

    @Transactional
    @Override
    public void deleteRoleById(int id) {
        roleRepository.deleteById(id);
    }
}
