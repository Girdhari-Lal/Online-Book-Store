package com.onlinebookstore.userservice.dto;

import lombok.Data;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
@Data
public class UserDTO {
    @NotEmpty(message = "Username is required")
    private String username;

    @NotEmpty(message = "Email is required")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message = "Email should be valid")
    private String email;

    @NotEmpty(message = "Password is required")
    @Size(min = 3, message = "Password must be at least 3 characters long")
    private String password;

    @NotNull(message = "First name cannot be null")
    @Size(min = 3, max = 20, message = "First name must be between 3 to 20 characters")
    private String firstName;

    @NotNull(message = "Last name cannot be null")
    @Size(min = 3, max = 20, message = "Last name must be between 3 to 20 characters")
    private String lastName;

    @NotNull(message = "Address cannot be null")
    @Size(min = 5, max = 50, message = "Address must be between 5 to 50 characters")
    private String address;

    @NotNull(message = "phone Number cannot be null")
    @Pattern(regexp = "^\\+?[0-9. ()-]{11,15}$", message = "Phone number is invalid")
    private String phoneNumber;
}
