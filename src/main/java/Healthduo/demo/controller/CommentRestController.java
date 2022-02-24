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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        log.info("CommentWrite(controller start)");
        Bbs bbs = bbsService.bbsfindById(bbsNo);
        Member member = memberService.memberfindById(loginMember);
        commentRestService.CommentSave(content, bbs, member);

    }

    @PostMapping("findComment")
    public List<CommentDTO> findComment(@RequestParam("bbsNo") Long bbsNo) {
        log.info("findComment(controller start)");
        List<CommentDTO> commentDTO = commentRestService.commentFind(bbsNo);
        log.info("commentDTO.size() =" + commentDTO.size());
        return commentDTO;
    }

    @DeleteMapping("commentDelete")
    public void commentDelete(@RequestParam("commentGroup") int commentGroup) {
        log.info("commentDelete(controller start)");
        commentRestService.commentDelete(commentGroup);
    }

    @PostMapping("childCommentWrite")
    public void childCommentWrite(@RequestParam("CommemtContent") String content,
                                  @RequestParam("bbsNo") Long bbsNo,
                                  @RequestParam("childinfo") String childinfo,
                                  @RequestParam("Seq") int seq,
                                  @SessionAttribute(name = "memberId", required = false) String loginMember) {
        log.info("childCommentWrite(controller start)");
        log.info("seq = "   + seq);
        Bbs bbs = bbsService.bbsfindById(bbsNo);
        Member member = memberService.memberfindById(loginMember);
        commentRestService.childCommentSave(content,bbs,member,childinfo, seq);

    }
}
