package com.sparta.chatservice.repositories;

import com.sparta.chatservice.entitiies.MemberChatroomMapping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberChatroomMappingRepository extends JpaRepository<MemberChatroomMapping, Long> {

}
