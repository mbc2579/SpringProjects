package org.example.auth.application.interfaces;

import org.example.auth.domain.UserAuth;
import org.example.user.domain.User;

public interface UserAuthRepository {

    UserAuth registerUser(UserAuth auth, User user);
    UserAuth loginUser(String email, String password, String fcmToken);
}
