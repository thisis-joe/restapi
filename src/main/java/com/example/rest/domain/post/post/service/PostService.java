package com.example.rest.domain.post.post.service;

import com.example.rest.domain.post.post.entity.Post;
import com.example.rest.domain.post.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    public void write(String title, String content) {
        postRepository.save(
                Post
                        .builder()
                        .title(title)
                        .content(content)
                        .build()
        );
    }
    public List<Post> getItems() {
        return postRepository.findAll();
    }
    public Optional<Post> getItem(long id) {
        return postRepository.findById(id);
    }
    public long count() {
        return postRepository.count();
    }
    public void delete(Post post) {
        postRepository.delete(post);
    }
    @Transactional
    public void modify(Post post, String title, String content) {
        post.setTitle(title);
        post.setContent(content);
    }
}