package com.sparta.chatservice.services;

import com.sparta.chatservice.entitiies.Chatroom;
import com.sparta.chatservice.entitiies.Member;
import com.sparta.chatservice.entitiies.MemberChatroomMapping;
import com.sparta.chatservice.repositories.ChatroomRepository;
import com.sparta.chatservice.repositories.MemberChatroomMappingRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatService {

    private final ChatroomRepository chatroomRepository;
    private final MemberChatroomMappingRepository memberChatroomMappingRepository;

    // 채팅방 만들기
    public Chatroom createChatroom(Member member, String title) {
        Chatroom chatroom = Chatroom.builder()
            .title(title)
            .createdAt(LocalDateTime.now())
            .build();

        chatroom = chatroomRepository.save(chatroom);

        MemberChatroomMapping memberChatroomMapping = chatroom.addMember(member);

        memberChatroomMapping = memberChatroomMappingRepository.save(memberChatroomMapping);

        return chatroom;
    }

    // 채팅방 참여
    public Boolean joinChatroom(Member member, Long chatroomId) {
        if (memberChatroomMappingRepository.existsByMemberIdAndChatroomId(member.getId(), chatroomId)) {
            log.info("이미 참여한 채팅방입니다.");
            return false;
        }

        Chatroom chatroom = chatroomRepository.findById(chatroomId).get();

        MemberChatroomMapping memberChatroomMapping = MemberChatroomMapping.builder()
            .member(member)
            .chatroom(chatroom)
            .build();

        memberChatroomMappingRepository.save(memberChatroomMapping);

        return true;
    }

    // 채팅방 나가기
    @Transactional
    public Boolean leaveChatroom(Member member, Long chatroomId) {
        if (!memberChatroomMappingRepository.existsByMemberIdAndChatroomId(member.getId(), chatroomId)) {
            log.info("참여하지 않은 방입니다.");
            return false;
        }

        memberChatroomMappingRepository.deleteByMemberIdAndChatroomId(member.getId(), chatroomId);

        return true;
    }

    // 참여한 채팅방 목록 전제 조회
    public List<Chatroom> getChatroomList(Member member) {
        List<MemberChatroomMapping> memberChatroomMappingList = memberChatroomMappingRepository.findAllByMemberId(member.getId());

        return memberChatroomMappingList.stream()
            .map(MemberChatroomMapping::getChatroom)
            .toList();
    }
}
