package Healthduo.demo.controller;

import Healthduo.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
@Slf4j
public class memberRestController {
    private final MemberService memberService;

    /**
     * 중복회원 검사
     * @param memberId
     * @return
     * 중복회원이 아니면 0 중복회원이면 1
     */
    @PostMapping("duplicatedMember")
    public int duplicatedMember(@RequestParam("memberId") String memberId) {
        log.info("duplicatedMember(controller start)");
       int checkIdNumber =  memberService.duplicatedMember(memberId);
        return checkIdNumber;
    }

    /**
     * 회원 삭제
     * 회원 삭제 성공시 1반환 실패히 0 반환
     * @param memberId
     * @param request
     * @return
     */
    @DeleteMapping("deletemember")
    public int deleteMember(@RequestParam("memberId") String memberId , HttpServletRequest request){
        log.info("deletemember(controller start)");
       int deleteCheckNumber =memberService.deleteMember(memberId);
        //세션을 삭제한다.
        if(deleteCheckNumber == 1) {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
        }
        return deleteCheckNumber;
    }
}

