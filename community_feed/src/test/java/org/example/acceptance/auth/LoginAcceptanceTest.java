package org.example.acceptance.auth;

import static org.example.acceptance.steps.LoginAcceptanceSteps.requestLoginGetResponseCode;
import static org.example.acceptance.steps.LoginAcceptanceSteps.requestLoginGetToken;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.example.acceptance.utils.AcceptanceTestTemplate;
import org.example.auth.application.dto.LoginRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LoginAcceptanceTest extends AcceptanceTestTemplate {
    private final String email = "email@email.com";

    @BeforeEach
    void setUp() {
        this.cleanUp();
        this.createUser(email);
    }

    @Test
    void givenEmailAndPassword_whenLogin_thenReturnToken() {
        // given
        LoginRequestDto dto = new LoginRequestDto(email, "password");

        // when
        String token = requestLoginGetToken(dto);

        // then
        assertNotNull(token);
    }

    @Test
    void givenEmailAndWrongPassword_whenLogin_thenReturnCodeNotZero() {
        // given
        LoginRequestDto dto = new LoginRequestDto(email, "wrong password");

        // when
        Integer code = requestLoginGetResponseCode(dto);

        // then
        assertEquals(400, code);
    }
}
