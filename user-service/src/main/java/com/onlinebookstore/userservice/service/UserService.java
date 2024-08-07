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
        List<User> userList = userRepository.findByActiveTrue();
        if(!userList.isEmpty()) {
            List<UserDTO> userDTOList = userList.stream().map(user -> {
                UserDTO userDTO = userMapper.convertToUserDTO(user);
                userDTO.setPassword(null);
                return userDTO;
            }).collect(Collectors.toList());
            return userDTOList;
        }
        throw new RuntimeException("No users found");
    }

    public UserDTO findUserByUsername(String username){
        Optional<User> optionalUser = userRepository.findByUsernameAndActiveTrue(username);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            UserDTO userDTO = userMapper.convertToUserDTO(user);
            userDTO.setPassword(null);
            return userDTO;
        }
        throw new RuntimeException("User with username " + username + " not found");
    }

    public UserDTO createUser(UserDTO userDTO){
        User user = userMapper.convertToUser(null ,userDTO);
        user.setActive(true);
        User savedUser = userRepository.save(user);
        return userMapper.convertToUserDTO(savedUser);
    }

    public UserDTO loginUser(String username, String password){
        Optional<User> optionalUser = userRepository.findByUsernameAndActiveTrue(username);
        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            if (password.equals(user.getPassword())){
                UserDTO userDTO = userMapper.convertToUserDTO(user);
                return userDTO;
            }
            throw new RuntimeException("Invalid Password");
        }
        throw new RuntimeException("user not exist!");
    }

    public UserDTO modifyUser(UserDTO userDTO, String username){
        Optional<User> optionalUser = userRepository.findByUsernameAndActiveTrue(username);
        if (optionalUser.isPresent()){
            User user = userMapper.convertToUser(optionalUser.get(), userDTO);
            user = userRepository.save(user);
            return userMapper.convertToUserDTO(user);
        }
        throw new RuntimeException("User with username " + username + " not found");
    }

    public String deactivateUser(String username, String password){
        Optional<User> optionalUser = userRepository.findById(username);
        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            if (password.equals(user.getPassword())){
                int deactivatedUser = userRepository.deactivateUser(username, password);
                if(deactivatedUser > 0) {
                    return "User deactivated successfully.";
                }
                throw new RuntimeException("User is already deactivated");
            }
            throw new RuntimeException("Invalid Password");
        }
        throw new RuntimeException("user not exist!");
    }
}
