package com.beta.replyservice;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReplyControllerIntegrationTest {

    @LocalServerPort
    private int port;

    private final TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void testValidRuleApplication() {
        String url = "http://localhost:" + port + "/v2/reply/12-kbzw9ru";
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        assertEquals("{\"statusCode\":200,\"message\":\"5a8973b3b1fafaeaadf10e195c6e1dd4\"}", responseEntity.getBody());
    }

    @Test
    public void testValidRuleFormat() {
        String url = "http://localhost:" + port + "/v2/reply/11-kbzw9ru";
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        assertEquals("{\"statusCode\":200,\"message\":\"kbzw9ru\"}", responseEntity.getBody());
    }

    @Test
    public void testInvalidRuleApplication() {
        String url = "http://localhost:" + port + "/v2/reply/13-kbzw9ru";
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        assertEquals("{\"statusCode\":400,\"message\":\"Invalid input\"}", responseEntity.getBody());
    }

    @Test
    public void testInvalidRuleFormat() {
        String url = "http://localhost:" + port + "/v2/reply/invalid-kbzw9ru";
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        assertEquals("{\"statusCode\":400,\"message\":\"Invalid input\"}", responseEntity.getBody());
    }

    @Test
    public void testEmptyMessage() {
        String url = "http://localhost:" + port + "/v2/reply/12-";
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        assertEquals("{\"statusCode\":200,\"message\":\"Message is empty\"}", responseEntity.getBody());
    }

    @Test
    public void testNoRulesApplied() {
        String url = "http://localhost:" + port + "/v2/reply/-kbzw9ru";
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        assertEquals("{\"statusCode\":400,\"message\":\"Invalid input\"}", responseEntity.getBody());
    }
}