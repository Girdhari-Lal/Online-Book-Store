package com.onlinebookstore.userservice.repository;

import com.onlinebookstore.userservice.entity.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class UserRepositoryTest {

    UserRepository userRepository;
    @Autowired
    public UserRepositoryTest(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Test
    void findByUsername() {
        Optional<User> result = userRepository.findById("verma");
        assertThat(result).isPresent();
        assertThat(result.get()).isNotNull();
        assertThat(result.get().getUsername()).isEqualTo("verma");
    }

    @AfterEach
    void tearDown(){
        System.out.println("Delete successfully");
        userRepository.deleteUser("verma");
    }
}