package com.onlinebookstore.userservice.service;

import com.onlinebookstore.userservice.dto.UserDTO;
import com.onlinebookstore.userservice.entity.User;
import com.onlinebookstore.userservice.mapper.UserMapper;
import com.onlinebookstore.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    public final UserRepository userRepository;
    public final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<UserDTO> listUsers(){
        List<User> userList = userRepository.findAll();
        if(!userList.isEmpty()) {
            List<UserDTO> userDTOList = userList.stream().map(user -> {
                UserDTO userDTO = userMapper.UserToUserDTO(user);
                return userDTO;
            }).collect(Collectors.toList());
            return userDTOList;
        }
        throw new RuntimeException("Not found");
    }

    public UserDTO findUserByUsername(String username){
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            UserDTO userDTO = userMapper.UserToUserDTO(user);
            return userDTO;
        }
        throw new RuntimeException("Not found");
    }

    public UserDTO saveUser(User user){
        User userSaved = userRepository.save(user);
        UserDTO userDTO = userMapper.UserToUserDTO(userSaved);
        return userDTO;
    }

    public UserDTO updateUser(User user, String username){
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent()){
            UserDTO userDTO = userMapper.UserToUserDTO(optionalUser.get());
            userMapper.updateUserFromDTO(userDTO, user);
            User savedUser = userRepository.save(user);
            return userMapper.UserToUserDTO(savedUser);
        }
        throw new RuntimeException("Invalid Input");
    }

    public  String deleteAllUser(){
        userRepository.deleteAll();
        return "Delete all user successfully";
    }

    public String deleteUser(String username){
        userRepository.deleteUser(username);
        return "Delete user successfully";
    }
}
