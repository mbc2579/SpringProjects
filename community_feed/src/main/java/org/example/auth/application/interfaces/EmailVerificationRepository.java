package org.example.auth.application.interfaces;

import org.example.auth.domain.Email;

public interface EmailVerificationRepository {
    void createEmailVerification(Email email, String token);
}
