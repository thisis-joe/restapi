package com.example.rest.domain.post.post.controller;
import com.example.rest.domain.post.post.entity.Post;
import com.example.rest.domain.post.post.service.PostService;
import com.example.rest.global.dto.RsData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class ApiV1PostController {
    private final PostService postService;
    @GetMapping
    public List<Post> getItems() { //여러 건 조회
        return postService.getItems();
    }
    @GetMapping("{id}")
    public Post getItem(@PathVariable long id) { //단 건 조회
        return postService.getItem(id).get();
    }
    @DeleteMapping("/{id}")
    public RsData delete(@PathVariable long id) {
        Post post = postService.getItem(id).get();
        postService.delete(post);

        return new RsData("200-1", "%d번 글 삭제가 완료되었습니다.".formatted(id));
    }

    @AllArgsConstructor
    @Getter
    public static class ModifyForm {
        private String title;
        private String content;
    }
    @PutMapping("{id}")
    public RsData modify(@PathVariable long id, @RequestBody ModifyForm form) {
        Post post = postService.getItem(id).get();
        postService.modify(post, form.getTitle(), form.getContent());

        return new RsData("200-2", "%d번 글 수정이 완료되었습니다.".formatted(id));
    }

    @AllArgsConstructor
    @Getter
    public static class WriteForm {
        private String title;
        private String content;
    }
    @PostMapping
    public RsData write(@RequestBody WriteForm form) {
        postService.write(form.getTitle(), form.getContent());
        return new RsData("200-1", "글 작성이 완료되었습니다."
        );
    }

}