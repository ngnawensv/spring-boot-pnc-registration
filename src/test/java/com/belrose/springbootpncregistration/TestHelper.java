package com.belrose.springbootpncregistration;

import com.belrose.springbootpncregistration.model.IpApiResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

public class TestHelper {
    ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);

    public IpApiResponse getIpApiResponse() throws IOException{
        ClassPathResource resource = new ClassPathResource("/mock-data/ipApiResponse.json",IpApiResponse.class);
        return objectMapper.readValue(resource.getInputStream(),IpApiResponse.class);
    }

    public String jsonObjectAsString(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }
}
