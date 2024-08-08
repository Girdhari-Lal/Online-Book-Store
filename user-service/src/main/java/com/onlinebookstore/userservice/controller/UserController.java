package com.onlinebookstore.userservice.controller;

import com.onlinebookstore.userservice.dto.UserDTO;
import com.onlinebookstore.userservice.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    public final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public List<UserDTO> listUsers(){
        return userService.listUsers();
    }

    @GetMapping("/login")
    public UserDTO getUser(@RequestParam("username") String username, @RequestParam("password") String password){
        return userService.getUser(username, password);
    }

    @PostMapping("/register")
    public UserDTO createUser(@Valid @RequestBody UserDTO userDTO){
        return userService.createUser(userDTO);
    }

    @PutMapping
    public UserDTO updateUser(@Valid @RequestBody UserDTO userDTO, @RequestParam("username") String username, @RequestParam("password") String password){
        return userService.modifyUser(userDTO, username, password);
    }

    @DeleteMapping
    public String deactivateUser(@RequestParam("username") String username, @RequestParam("password") String password){
        return userService.deactivateUser(username, password);
    }
}
