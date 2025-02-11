package com.example.rest.domain.member.member.controller;
import com.example.rest.domain.member.member.dto.MemberDto;
import com.example.rest.domain.member.member.entity.Member;
import com.example.rest.domain.member.member.service.MemberService;
import com.example.rest.global.dto.RsData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class ApiV1MemberController {
    private final MemberService memberService;
    record JoinReqBody(@NotBlank @Length(min = 3) String username,
                       @NotBlank @Length(min = 3) String password,
                       @NotBlank @Length(min = 3) String nickname) {
    }
    @PostMapping
    public RsData<MemberDto> join(@RequestBody @Valid JoinReqBody body) {
        Member member = memberService.join(body.username(), body.password(), body.nickname());
        return new RsData<>(
                "200-1",
                "회원 가입이 완료되었습니다.",
                new MemberDto(member)
        );
    }
}