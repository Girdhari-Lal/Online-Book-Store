package com.onlinebookstore.userservice.mapper;

import com.onlinebookstore.userservice.dto.UserDTO;
import com.onlinebookstore.userservice.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDTO convertToUserDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setAddress(user.getAddress());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        return userDTO;
    }

    public User convertToUser(UserDTO userDTO){
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setAddress(userDTO.getAddress());
        user.setEmail(userDTO.getEmail());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        return user;
    }

    public void updateExistingUser(User previousUser, User updatedUser){
        previousUser.setUsername(updatedUser.getUsername());
        previousUser.setFirstName(updatedUser.getFirstName());
        previousUser.setLastName(updatedUser.getLastName());
        previousUser.setAddress(updatedUser.getAddress());
        previousUser.setEmail(updatedUser.getEmail());
        previousUser.setPhoneNumber(updatedUser.getPhoneNumber());
    }
}
