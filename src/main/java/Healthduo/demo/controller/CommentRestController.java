package Healthduo.demo.controller;

import Healthduo.demo.domain.Bbs;
import Healthduo.demo.domain.Member;
import Healthduo.demo.dto.BbsDTO;
import Healthduo.demo.dto.CommentDTO;
import Healthduo.demo.dto.MemberDTO;
import Healthduo.demo.service.BbsService;
import Healthduo.demo.service.CommentRestService;
import Healthduo.demo.service.MemberService;
import Healthduo.demo.web.Method;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CommentRestController {
    private final Method method;
    private final BbsService bbsService;
    private final MemberService memberService;
    private final CommentRestService commentRestService;

    @PostMapping("CommentWrite")
    public void CommentWrite(@RequestParam("CommemtContent") String content,
                             @RequestParam("bbsNo") Long bbsNo,
                             @SessionAttribute(name = "memberId", required = false) String loginMember) {
     Bbs bbs       = bbsService.bbsfindById(bbsNo);
     Member member = memberService.memberfindById(loginMember);
     commentRestService.CommentSave(content,bbs,member);

    }

}