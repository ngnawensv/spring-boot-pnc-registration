package com.belrose.springbootpncregistration.service.impl;

import ch.qos.logback.classic.spi.IThrowableProxy;
import com.belrose.springbootpncregistration.config.AppConfig;
import com.belrose.springbootpncregistration.dto.UserDto;
import com.belrose.springbootpncregistration.exception.UserException;
import com.belrose.springbootpncregistration.model.IpApiResponse;
import com.belrose.springbootpncregistration.model.User;
import com.belrose.springbootpncregistration.model.UserResponse;
import com.belrose.springbootpncregistration.service.UserService;
import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.UUID;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final WebClient ipApiWebClient;
    private final AppConfig appConfig;

    public UserServiceImpl(@Qualifier("ipApiWebClient") WebClient ipApiWebClient, AppConfig appConfig) {
        this.ipApiWebClient = ipApiWebClient;
        this.appConfig = appConfig;
    }

    @Override
    public UserResponse getData(UserDto userDto) {
       /* var response = ipApiWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(appConfig.getIpApiBaseUrl()+"/"+userDto.getIpAddress())
                        .build())
                .retrieve()
                .bodyToMono(IpApiResponse.class)
                .block();
        if(response==null || !response.getCountry().equals("Canada")){
            log.error("UserServiceImpl->getDate():response {}",response);
            throw new UserException("User is not eligible to register");
        }
        log.info("UserServiceImpl->getDate():response {}",response);
        return UserResponse.builder()
                .id(UUID.randomUUID().toString())
                .message(String.format("Welcome %s in this %s", userDto.getUsername(), response.getCity())).build();
     */
        return UserResponse.builder()
                .id(UUID.randomUUID().toString())
                .message(String.format("Welcome %s in this %s", userDto.getUsername(), "")).build();
    }
}