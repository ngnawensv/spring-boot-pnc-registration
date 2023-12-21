package com.belrose.springbootpncregistration.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
    @NotBlank(message = "password may not be blank")
    private String username;
    @NotBlank(message = "password may not be blank")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[_#$%]).{8,}$", message = "Invalid password")
    private String password;
    @NotBlank(message = "ipAddress may not be blank")
    private String ipAddress;
}
