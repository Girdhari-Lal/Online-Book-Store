package com.onlinebookstore.userservice.service;

import com.onlinebookstore.userservice.dto.UserDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UserServiceTest {

    private final UserService userService;

    @Autowired
    public UserServiceTest(UserService userService){
        this.userService = userService;
    }

//    @Test
//    void getUser() {
//        String username = "verma";
//        UserDTO userDTO = userService.findUserByUsername("verma");
//        assertEquals(username, userDTO.getUsername());
//    }

}