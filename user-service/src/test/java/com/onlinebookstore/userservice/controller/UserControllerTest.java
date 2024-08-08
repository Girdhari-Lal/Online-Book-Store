package com.onlinebookstore.userservice.controller;

import com.jayway.jsonpath.JsonPath;
import com.onlinebookstore.userservice.dto.UserDTO;
import com.onlinebookstore.userservice.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private UserDTO userDTO;

    @BeforeEach
    void setUp() {
        userDTO = new UserDTO();
        userDTO.setUsername("verma");
        userDTO.setPassword("123");
        userDTO.setFirstName("verma");
        userDTO.setLastName("kumar");
        userDTO.setEmail("verma@example.com");
        userDTO.setAddress("123 main street");
        userDTO.setPhoneNumber("03331212120");
    }

//    @Test
//    void findUserByUsername() throws Exception {
//        Mockito.when(userService.findUserByUsername("verma")).thenReturn(userDTO);
//        mockMvc.perform(MockMvcRequestBuilders.get("/users/verma").contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect((ResultMatcher) jsonPath("$.username")
//                        .value(userDTO.getUsername()));
//    }

}