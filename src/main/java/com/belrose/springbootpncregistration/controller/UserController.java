package com.belrose.springbootpncregistration.controller;

import com.belrose.springbootpncregistration.dto.UserDto;
import com.belrose.springbootpncregistration.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //@Valid annotation is used to validate incoming request
    @PostMapping("/ipAddress")
    public ResponseEntity<String> getDate(@RequestBody @Valid UserDto userDto){
        var response =userService.getData(userDto);
        log.info("UserController->getDate():response {}",response);
        return ResponseEntity.ok(response);
    }
}
