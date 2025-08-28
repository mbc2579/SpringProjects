package org.example.auth.application.interfaces;

import org.example.auth.domain.Email;

public interface EmailSendRepository {
    void sendEmail(Email email, String randomToken);
}
