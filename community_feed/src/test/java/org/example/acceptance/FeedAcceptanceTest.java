package org.example.acceptance;

import static org.example.acceptance.steps.FeedAcceptanceSteps.requestCreatePost;
import static org.example.acceptance.steps.FeedAcceptanceSteps.requestFeed;
import static org.example.acceptance.steps.FeedAcceptanceSteps.requestFeedCode;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.example.acceptance.utils.AcceptanceTestTemplate;
import org.example.post.application.dto.CreatePostRequestDto;
import org.example.post.domain.content.PostPublicationState;
import org.example.post.ui.dto.GetPostContentResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FeedAcceptanceTest extends AcceptanceTestTemplate {

    private String token;

    /**
     * User 1 --- follow ---> User 2
     * User 1 --- follow ---> User 3
     */
    @BeforeEach
    void setUp() {
        super.init();
        this.token = login("user1@test.com");
    }

    /**
     * User 2 create Post 1
     * User 1 Get Post 1 From Feed
     */
    @Test
    void givenUserHasFollowerAndCreatePost_whenFollowerUserRequestFeed_thenFollowerCanGetPostFromFeed() {
        // given
        CreatePostRequestDto dto = new CreatePostRequestDto(2L, "user 1 can get this post", PostPublicationState.PUBLIC);
        Long createdPostId = requestCreatePost(dto);

        // when, 팔로워 피드를 요청
        List<GetPostContentResponseDto> result = requestFeed(token);

        //then
        assertEquals(1, result.size());
        assertEquals(createdPostId, result.get(0).getId());

    }

    @Test
    void givenUserHasFollower_whenFollowerUserRequestFeedWithInvalidToken_thenFollowerCanGetPostFromFeed() {
        // given
        // when, 팔로워 피드를 요청
        Integer code = requestFeedCode("abcd");

        //then
        assertEquals(400, code);
    }
}
