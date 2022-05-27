package com.abbeal.demo.repository;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import com.abbeal.demo.model.AppUser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class AppUserRepositoryTest {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private AppUserRepository appUserRepository;

    @Test
    void injectedComponentsAreNotNull() {
        Assertions.assertNotNull(dataSource);
        Assertions.assertNotNull(jdbcTemplate);
        Assertions.assertNotNull(entityManager);
        Assertions.assertNotNull(appUserRepository);
    }

    @Test
    void canAddAndRetrieveUserInDB() {
        AppUser userToSave = new AppUser("Frank", 65);
        AppUser savedUser = appUserRepository.save(userToSave);
        Optional<AppUser> retrievedUser = appUserRepository.findById(savedUser.getId());

        Assertions.assertEquals(retrievedUser.get(), savedUser);
    }
}
