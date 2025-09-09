package org.example.message.application.interfaces;

import org.example.user.domain.User;

public interface MessageRepository {

    void sendLikeMessage(User sender, User targetUser);

}
