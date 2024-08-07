package com.onlinebookstore.userservice.service;

import com.onlinebookstore.userservice.dto.UserDTO;
import com.onlinebookstore.userservice.entity.User;
import com.onlinebookstore.userservice.mapper.UserMapper;
import com.onlinebookstore.userservice.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    private UserService userService;
    @Mock
    private UserMapper userMapper;

    @BeforeEach
    void stepUp(){
        this.userService = new UserService(userRepository, userMapper);
    }

    @Test
    void listUsers() {
        List<UserDTO> userDTOList = userService.listUsers();
        System.out.println(userDTOList);
        verify(userRepository).findAll();
    }

//    @Test
//    void deleteUser() {
//        userService.deleteUser("verma");
//        verify(userRepository).deleteUser("verma");
//    }
}