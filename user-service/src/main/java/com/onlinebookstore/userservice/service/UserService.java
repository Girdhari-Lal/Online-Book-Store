package com.onlinebookstore.userservice.service;

import com.onlinebookstore.userservice.dto.UserDTO;
import com.onlinebookstore.userservice.entity.User;
import com.onlinebookstore.userservice.exception.UserServiceException;
import com.onlinebookstore.userservice.mapper.UserMapper;
import com.onlinebookstore.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
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

    private User validateUserLogin(String username, String password){
        User user = userRepository.findById(username).orElseThrow(() -> new UserServiceException("User does not exist!"));
        if (!password.equals(user.getPassword())) {
            throw new UserServiceException("Invalid Password");
        }
        if (!user.isActive()) {
            throw new UserServiceException("User account is deactivated");
        }
        return user;
    }

    public List<UserDTO> listUsers(){
        List<User> userList = userRepository.findByActiveTrue();
        if(userList.isEmpty()) {
            throw new UserServiceException("No users found");
        }
        return userList.stream().map(user -> {
            UserDTO userDTO = userMapper.convertToUserDTO(user);
            return userDTO;
        }).collect(Collectors.toList());
    }

    public UserDTO createUser(UserDTO userDTO){
        User user = userMapper.convertToUser(null, userDTO);
        User savedUser = userRepository.save(user);
        return userMapper.convertToUserDTO(savedUser);
    }

    public UserDTO getUser(String username, String password){
        User user = validateUserLogin(username, password);
        UserDTO userDTO = userMapper.convertToUserDTO(user);
        return userDTO;
    }

    public UserDTO modifyUser(UserDTO userDTO, String username, String password){
        User user = validateUserLogin(username, password);
        User updatedUser = userMapper.convertToUser(user, userDTO);
        User savedUser = userRepository.save(updatedUser);
        return userMapper.convertToUserDTO(savedUser);
    }

    public String deactivateUser(String username, String password){
        User user = validateUserLogin(username, password);
        user.setActive(false);
        userRepository.save(user);
        return "User deactivated successfully.";
    }
}
