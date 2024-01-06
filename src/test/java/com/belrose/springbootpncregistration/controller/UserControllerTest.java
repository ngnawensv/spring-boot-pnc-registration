package com.belrose.springbootpncregistration.controller;

import com.belrose.springbootpncregistration.dto.UserDto;
import com.belrose.springbootpncregistration.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@WebFluxTest(UserController.class)
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserControllerTest {
    @Autowired
    WebTestClient webTestClient;
    @MockBean
    UserService userService;



    private final UserDto userDto = UserDto.builder()
            .username("Samy")
            .password("Password_123")
            .ipAddress("10.10.10.10")
           // .email("smay@gmail.com")
            .build();
    private final UserDto userDtoWithWrongPassword = UserDto.builder()
            .username("Samy")
            .password("Password")
            .ipAddress("10.10.10.10")
            //.email("smay@gmail.com")
            .build();

    @Test
    void getData_UserDto_ReturnString(){
        given(userService.getData(userDto)).willReturn("message");
        String path = "http://localhost:8080/api/v1/ipAddress";
        webTestClient.post()
                .uri(path)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(userDto),UserDto.class)
                .exchange()
                .expectStatus()
                .is2xxSuccessful();
    }

    @Test
    void getData_UserDto_ReturnErrors(){
        given(userService.getData(userDtoWithWrongPassword)).willReturn("message");
        String path = "http://localhost:8080/api/v1/ipAddress";
        webTestClient.post()
                .uri(path)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(userDtoWithWrongPassword),UserDto.class)
                .exchange()
                .expectStatus()
                .is4xxClientError();
    }
}
