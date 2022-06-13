package Healthduo.demo.controller;

import Healthduo.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
        log.info("checkIdNumber = " + checkIdNumber);

        return checkIdNumber;
    }
}

