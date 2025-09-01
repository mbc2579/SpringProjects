package org.example.post.ui;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.common.principal.AuthPrincipal;
import org.example.common.principal.UserPrincipal;
import org.example.common.ui.Response;
import org.example.post.repository.post_queue.UserPostQueueQueryRepository;
import org.example.post.ui.dto.GetPostContentResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feed")
@RequiredArgsConstructor
public class FeedController {

    private final UserPostQueueQueryRepository queueQueryRepository;

    @GetMapping("")
    public Response<List<GetPostContentResponseDto>> getPostFeed(@AuthPrincipal UserPrincipal userPrincipal, Long lastPostId) {
        List<GetPostContentResponseDto> result = queueQueryRepository.getContentResponse(userPrincipal.getUserId(), lastPostId);
        return Response.ok(result);
    }
}
