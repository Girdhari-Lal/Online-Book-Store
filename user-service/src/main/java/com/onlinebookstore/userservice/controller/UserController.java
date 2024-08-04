package com.onlinebookstore.userservice.controller;

import com.onlinebookstore.userservice.dto.UserDTO;
import com.onlinebookstore.userservice.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/{username}")
    public UserDTO findUserByUsername(@PathVariable("username") String username){
        return userService.findUserByUsername(username);
    }

    @GetMapping("/login")
    public UserDTO loginUser(@RequestParam("username") String username, @RequestParam("password") String password){
        return userService.loginUser(username, password);
    }

    @PostMapping("/register")
    public UserDTO saveUser(@Valid @RequestBody UserDTO userDTO){
        return userService.saveUser(userDTO);
    }

    @PutMapping("/{username}")
    public UserDTO updateUser(@Valid @RequestBody UserDTO userDTO, @PathVariable("username") String username){
        return userService.modifyUser(userDTO, username);
    }

    @DeleteMapping("/{username}")
    public String deleteUser(@PathVariable("username") String username){
        return userService.deleteUser(username);
    }
}
