package com.belrose.springbootpncregistration.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@Setter
@Getter
public class AppConfig {

    @Value("${api.base}")
    private String ipApiBaseUrl;


    @Bean
    @Qualifier("ipApiWebClient")
    public WebClient getIpApiWebClient() {
        return WebClient.builder()
                .baseUrl(this.getIpApiBaseUrl())
                .build();
    }
}
