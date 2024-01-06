package com.belrose.springbootpncregistration.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collation = "users")
public class UserDto implements Serializable {
 @NotBlank(message = "Invalid username: Empty username")
 @Size(min = 3, max = 30, message = "Invalid username: Must be of 3 - 30 characters")
 private String username;

 @NotBlank(message = "password is mandatory")
 @Pattern(regexp = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[_#$%]).{8,}$",
         message = "Invalid password: Must contain at least: 1 number, 1 capital letter, 1special character _#$%")
 private String password;

 @NotBlank(message = "IP address is mandatory")
 private String ipAddress;

/* @NotBlank(message = "Email is mandatory")
 @Email(message = "Invalid email")
 private String email;*/
}
