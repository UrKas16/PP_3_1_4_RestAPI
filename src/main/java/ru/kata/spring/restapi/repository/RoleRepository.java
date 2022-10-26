package ru.kata.spring.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.kata.spring.restapi.model.Role;

@EnableJpaRepositories
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
