package com.belrose.springbootpncregistration.service.impl;

import com.belrose.springbootpncregistration.config.AppConfig;
import com.belrose.springbootpncregistration.dto.UserDto;
import com.belrose.springbootpncregistration.exception.UserException;
import com.belrose.springbootpncregistration.model.IpApiResponse;
import com.belrose.springbootpncregistration.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.UUID;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final WebClient ipApiWebClient;
    private final AppConfig appConfig;

    @Autowired
    public UserServiceImpl(@Qualifier("ipApiWebClient") WebClient ipApiWebClient, AppConfig appConfig) {
        this.ipApiWebClient = ipApiWebClient;
        this.appConfig = appConfig;
    }

    public UserServiceImpl(String ipApiWebBaseUrl, AppConfig appConfig) {
        this.ipApiWebClient = WebClient.create(ipApiWebBaseUrl);
        this.appConfig = appConfig;
    }

    @Override
    public String getData(UserDto userDto) {
        String path = String.format("%s%s",appConfig.getIpApiBaseUrl(),userDto.getIpAddress());
        var response = ipApiWebClient
                .get()
                .uri(path)
                .retrieve()
                .bodyToMono(IpApiResponse.class)
                .block();
        if (response == null || !response.getCountry().equals("Canada")) {
            log.error("UserServiceImpl->getDate():response {}", response);
            throw new UserException("User is not eligible to register");
        }
        log.info("UserServiceImpl->getDate():response {}", response);
        return String.format("%s===>Welcome %s to %s", UUID.randomUUID(),userDto.getUsername(), response.getCity());
    }
}