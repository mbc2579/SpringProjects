package org.example.post.application;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.example.post.application.dto.LikeRequestDto;
import org.example.post.application.dto.UpdateCommentRequestDto;
import org.example.post.domain.comment.Comment;
import org.junit.jupiter.api.Test;

class CommentServiceTest extends PostApplicationTestTemplate{

    @Test
    void givenCreateCommentRequestDto_whenCreateComment_thenReturnComment() {
        // when
        Comment comment = commentService.createComment(commentRequestDto);

        // then
        String content = comment.getContent();
        assertEquals(commentContentText, content);
    }

    @Test
    void givenCreateComment_whenUpdateComment_thenReturnUpdatedComment() {
        // given
        Comment comment = commentService.createComment(commentRequestDto);

        // when
        UpdateCommentRequestDto updateCommentRequestDto = new UpdateCommentRequestDto(user.getId(), "updated-content");
        Comment updatedComment = commentService.updateComment(comment.getId(), updateCommentRequestDto);

        // then
        assertEquals(comment.getId(), updatedComment.getId());
        assertEquals(comment.getAuthor(), updatedComment.getAuthor());
        assertEquals(comment.getContent(), updatedComment.getContent());
    }

    @Test
    void givenComment_whenLikeComment_thenReturnCommentWithLike() {
        // given
        Comment comment = commentService.createComment(commentRequestDto);

        // when
        LikeRequestDto likeRequestDto = new LikeRequestDto(comment.getId(), otherUser.getId());
        commentService.likeComment(likeRequestDto);

        // then
        assertEquals(1, comment.getLikeCount());
    }

    @Test
    void givenComment_whenUnlikeComment_thenReturnCommentWithoutLike() {
        // given
        Comment comment = commentService.createComment(commentRequestDto);

        // when
        LikeRequestDto likeRequestDto = new LikeRequestDto(comment.getId(), otherUser.getId());
        commentService.likeComment(likeRequestDto);
        commentService.unlikeComment(likeRequestDto);

        // then
        assertEquals(0, comment.getLikeCount());
    }
}
