package com.belrose.springbootpncregistration.service;

import com.belrose.springbootpncregistration.TestHelper;
import com.belrose.springbootpncregistration.config.AppConfig;
import com.belrose.springbootpncregistration.dto.UserDto;
import com.belrose.springbootpncregistration.exception.UserException;
import com.belrose.springbootpncregistration.service.impl.UserServiceImpl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class UserServiceTest {
    @Autowired
    private AppConfig appConfig;
    private static MockWebServer mockWebServer;
    private UserService userService;
    private final TestHelper testHelper = new TestHelper();
    private final UserDto userDto = UserDto.builder()
            .username("Samy")
            .password("Password_123")
            .ipAddress("10.10.10.10")
            //.email("smay@gmail.com")
            .build();

    @BeforeEach
    public void initialize(@Value("${server.port}") int port) throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start(port);
        String baseUrl = String.format("http://localhost:%s", port);
        userService = new UserServiceImpl(baseUrl,appConfig);
    }

    @AfterEach
    public void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    void getData_UserDto_ReturnString() throws IOException, InterruptedException {
        MockResponse mockResponse=new MockResponse()
                .setBody(testHelper.jsonObjectAsString(testHelper.getIpApiResponse()))
                .setResponseCode(200)
                .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
        mockWebServer.enqueue(mockResponse);

        var response = userService.getData(userDto);
        RecordedRequest recordedRequest = mockWebServer.takeRequest();

        assertNotNull(response);
        assertTrue(response.contains("Welcome"));
    }

    @Test
    void getData_UserDto_ThrowException() {
        MockResponse mockResponse=new MockResponse()
                .setBody("")
                .setResponseCode(200)
                .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
        mockWebServer.enqueue(mockResponse);

        assertThrowsExactly(UserException.class,()->userService.getData(userDto));
    }
}
