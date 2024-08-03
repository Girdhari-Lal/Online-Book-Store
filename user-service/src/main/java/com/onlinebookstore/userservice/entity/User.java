package com.onlinebookstore.userservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Table(name = "user")
@Entity
public class User {
    @Id
    @Column(name = "username")
    @NotEmpty(message = "Username is required")
    private String username;

    @Column(name = "password")
    @NotEmpty(message = "Password is required")
    @Size(min = 3, message = "Password must be at least 3 characters long")
    private String password;

    @Column(name = "email")
    @NotEmpty(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotNull(message = "First name cannot be null")
    @Size(min = 3, max = 20, message = "First name must be between 3 and 20 characters")
    @Column(name = "first_name", nullable = false, length = 20)
    private String firstName;

    @NotNull(message = "Last name cannot be null")
    @Size(min = 3, max = 20, message = "Last name must be between 3 and 20 characters")
    @Column(name = "last_name", nullable = false, length = 20)
    private String lastName;

    @NotNull(message = "Address cannot be null")
    @Size(min = 10, max = 50, message = "Address must be between 10 and 50 characters")
    @Column(name = "address", nullable = false, length = 50)
    private String address;

    @Column(name = "phone_number")
    @Pattern(regexp = "^\\+?[0-9. ()-]{11,13}$", message = "Phone number is invalid")
    private String phoneNumber;

}