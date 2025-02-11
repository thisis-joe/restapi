package com.example.rest.global;

import com.example.rest.domain.member.member.entity.Member;
import com.example.rest.domain.member.member.service.MemberService;
import com.example.rest.domain.post.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;

@Configuration
@RequiredArgsConstructor
public class BaseInitData {

    private final PostService postService;
    private final MemberService memberService;

    @Autowired
    @Lazy
    private BaseInitData self;

    @Bean
    public ApplicationRunner applicationRunner() {
        return args -> {
            self.memberInit();
            self.postInit();
        };
    }

    @Transactional
    public void memberInit() {
        if(memberService.count() > 0) {
            return;
        }
        // 회원 샘플데이터 생성
        memberService.join("system", "1234", "시스템");
        memberService.join("admin", "1234", "관리자");
        memberService.join("user1", "1234", "유저1");
        memberService.join("user2", "1234", "유저2");
        memberService.join("user3", "1234", "유저3");
    }

    @Transactional
    public void postInit() {
        if(postService.count() > 0) {
            return;
        }
        // 게시글 샘플데이터 생성
//        postService.write("title1", "content1");
//        postService.write("title2", "content2");
//        postService.write("title3", "content3");
        //멤버 엔티티 추가 후 게시글 샘플 데이터 생성
        Member user1 = memberService.findByUsername("user1").get();
        Member user2 = memberService.findByUsername("user2").get();

        postService.write(user1, "title1", "content1");
        postService.write(user1, "title2", "content2");
        postService.write(user2, "title3", "content3");
    }



}