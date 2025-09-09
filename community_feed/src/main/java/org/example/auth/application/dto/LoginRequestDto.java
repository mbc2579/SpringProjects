package org.example.auth.application.dto;

public record LoginRequestDto(String email, String password, String fcmToken) {

}
