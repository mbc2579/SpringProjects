package org.example.auth.repository;

import lombok.RequiredArgsConstructor;
import org.example.auth.application.interfaces.UserAuthRepository;
import org.example.auth.domain.UserAuth;
import org.example.auth.repository.entity.UserAuthEntity;
import org.example.auth.repository.jpa.JpaUserAuthRepository;
import org.example.user.application.interfaces.UserRepository;
import org.example.user.domain.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class UserAuthRepositoryImpl  implements UserAuthRepository {

    private final JpaUserAuthRepository jpaUserAuthRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserAuth registerUser(UserAuth auth, User user) {
        User savedUser = userRepository.save(user);
        UserAuthEntity userAuthEntity = new UserAuthEntity(auth, savedUser.getId());
        userAuthEntity = jpaUserAuthRepository.save(userAuthEntity);
        return userAuthEntity.toUserAuth();
    }

    @Override
    public UserAuth loginUser(String email, String password) {
        UserAuthEntity userAuthEntity = jpaUserAuthRepository.findById(email).orElseThrow();
        UserAuth userAuth = userAuthEntity.toUserAuth();

        if (!userAuth.matchPassword(password)) {
            throw new IllegalArgumentException("옳지 않은 비밀번호 입니다.");
        }

        return userAuth;
    }
}
