package com.belrose.springbootpncregistration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info=@Info(title = "spring-boot-pnc-registration Open APIs Service",version = "v1",
		description = "spring-boot-pnc-registration Open APIs for analyse some task",
		license = @License(name = "Apache 2.0",url = "https://www.apache.org/licences/LICENCE-2.0")))
public class SpringBootPncRegistrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootPncRegistrationApplication.class, args);
	}

}
