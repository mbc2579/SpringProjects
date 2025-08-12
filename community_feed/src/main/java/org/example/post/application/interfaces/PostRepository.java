package org.example.post.application.interfaces;

import org.example.post.domain.Post;

public interface PostRepository {

    Post save(Post post);

    Post findById(Long id);

}
