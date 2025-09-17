package com.sparta.chatservice.repositories;

import com.sparta.chatservice.entitiies.Chatroom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatroomRepository extends JpaRepository<Chatroom, Long> {

}
