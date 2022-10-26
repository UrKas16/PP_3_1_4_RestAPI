package ru.kata.spring.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.kata.spring.restapi.model.User;

@EnableJpaRepositories
public interface UserRepoitory extends JpaRepository<User, Long> {
    User findUserByUserAlias(String username);
}
