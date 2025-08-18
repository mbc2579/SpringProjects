package org.example.post.application.interfaces;

import org.example.post.domain.comment.Comment;

public interface CommentRepository {
    Comment save(Comment comment);
    Comment findById(Long id);
}
