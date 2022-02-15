package Healthduo.demo.controller;


import Healthduo.demo.domain.Member;
import Healthduo.demo.dto.BbsDTO;
import Healthduo.demo.dto.LoginDTO;
import Healthduo.demo.dto.MemberDTO;
import Healthduo.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.time.LocalDate;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @RequestMapping("/")
    public String Home() {
        return "Home";
    }

    @GetMapping("/members/new")
    public String createForm(Model model) {
        MemberDTO memberDTO = new MemberDTO();
        model.addAttribute("memberDTO", memberDTO);
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String memberSave(@Valid MemberDTO memberDTO, BindingResult result) {
        Member member = new Member(memberDTO.getMember_id(),memberDTO.getMember_password(),memberDTO.getMember_sex(),memberDTO.getMember_email(), LocalDate.now(),memberDTO.getMember_pnumber());
        log.info(member.toString());
        if (result.hasErrors()) {
            return "members/createMemberForm";
        }
        log.info("memberSave(controller start");
        memberService.memberSave(member);
        return "redirect:/";
    }
    @GetMapping("/login")
    public String getmemberLogin(LoginDTO loginDTO, Model model){
        model.addAttribute("loginDTO",loginDTO);
        return "/members/login";
    }

    @PostMapping("/login")
    public String postmemberLogin(@Valid LoginDTO loginDTO,BindingResult result, Model model){
        Member member = new Member(loginDTO.getMember_id(),loginDTO.getMember_password());

        log.info("LoginDTO"+ loginDTO.toString());
        log.info("postmemberLogin(controller start");
        int resultfind = memberService.memberfind(member);
        log.info(String.valueOf(resultfind));
        switch (resultfind) {
            case 1: result.addError(new ObjectError("loginDTO","등록된 아이디가 없습니다."));
            break;
            case 3: result.addError(new ObjectError("loginDTO","등록된 비밀번호가 틀렸습니다."));
            break;
        }
        if (result.hasErrors()) {
            return "members/login";
        }
        BbsDTO bbsDTO = new BbsDTO();
        model.addAttribute("bbsDTO",bbsDTO);
        return "bbs/write";

    }
}
