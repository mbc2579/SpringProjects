package org.example.auth.ui;

import lombok.RequiredArgsConstructor;
import org.example.auth.application.AuthService;
import org.example.auth.application.dto.LoginRequestDto;
import org.example.auth.application.dto.UserAccessTokenResponseDto;
import org.example.common.ui.Response;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final AuthService authService;

    @PostMapping
    public Response<UserAccessTokenResponseDto> login(@RequestBody LoginRequestDto dto) {
        System.out.println(dto.fcmToken());
        return Response.ok(authService.login(dto));
    }
}
