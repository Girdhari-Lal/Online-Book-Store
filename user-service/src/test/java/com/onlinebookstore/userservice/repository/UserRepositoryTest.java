package com.onlinebookstore.userservice.repository;

import com.onlinebookstore.userservice.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class UserRepositoryTest {

    private final UserRepository userRepository;

    private final TestEntityManager entityManager;

    @Autowired
    public UserRepositoryTest(TestEntityManager entityManager, UserRepository userRepository){
        this.userRepository = userRepository;
        this.entityManager = entityManager;
    }

    @BeforeEach
    void setUp() {
        User user = new User();
        user.setUsername("verma");
        user.setPassword("123");
        user.setFirstName("verma");
        user.setLastName("kumar");
        user.setEmail("verma@example.com");
        user.setActive(true);
        user.setAddress("123 main street");
        user.setPhoneNumber("03331212120");
        entityManager.persist(user);
    }

    @Test
    public void getUser(){
        User found = userRepository.findById("verma").get();
        assertEquals(found.getUsername(), "verma");
    }
    @Test

    void findByUsername() {
        Optional<User> result = userRepository.findById("verma");
        assertThat(result).isPresent();
        assertThat(result.get()).isNotNull();
        assertThat(result.get().getUsername()).isEqualTo("verma");
    }

}