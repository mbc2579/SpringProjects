package org.example.acceptance.utils;

import static org.example.acceptance.steps.SignUpAcceptanceSteps.registerUser;
import static org.example.acceptance.steps.SignUpAcceptanceSteps.requestSendEmail;
import static org.example.acceptance.steps.SignUpAcceptanceSteps.requestVerifyEmail;
import static org.example.acceptance.steps.UserAcceptanceSteps.followUser;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.auth.application.dto.CreateUserAuthRequestDto;
import org.example.auth.application.dto.SendEmailRequestDto;
import org.example.user.application.dto.FollowUserRequestDto;
import org.springframework.stereotype.Component;

@Component
public class DataLoader {

    @PersistenceContext
    private EntityManager entityManager;

    public void loadData() {
        // user 1, 2, 3 생성
        for (int i = 1; i < 4; i++) {
            createUser("user" + i + "@test.com");
        }

        followUser(new FollowUserRequestDto(1L, 2L));
        followUser(new FollowUserRequestDto(1L, 3L));
    }

    public String getEmailToken(String email) {
        return entityManager.createNativeQuery("SELECT token FROM community_email_verification WHERE email = ?", String.class)
                .setParameter(1, email)
                .getSingleResult()
                .toString();
    }

    public boolean isEmailVerified(String email) {
        return entityManager.createQuery("SELECT isVerified From EmailVerificationEntity WHERE email = :email", Boolean.class)
            .setParameter("email", email)
            .getSingleResult();
    }

    public Long getUserId(String email) {
        return entityManager.createQuery("SELECT userId From UserAuthEntity WHERE email = :email", Long.class)
            .setParameter("email", email)
            .getSingleResult();
    }

    public void createUser(String email) {
        requestSendEmail(new SendEmailRequestDto(email));
        String token = getEmailToken(email);
        requestVerifyEmail(email, token);
        registerUser(new CreateUserAuthRequestDto(email, "password", "USER", "name", ""));
    }
}
