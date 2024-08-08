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
                return userDTO;
            }).collect(Collectors.toList());
            return userDTOList;
        }
        throw new RuntimeException("No users found");
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

    private User validateUserLogin(String username, String password){
        Optional<User> optionalUser = userRepository.findById(username);
        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            if (password.equals(user.getPassword())){
                if(user.isActive()){
                    return user;
                }
                throw new RuntimeException("User account is already deactivated");
            }
            throw new RuntimeException("Invalid Password");
        }
        throw new RuntimeException("user not exist!");
    }
}
