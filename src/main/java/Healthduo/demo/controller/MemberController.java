package Healthduo.demo.controller;


import Healthduo.demo.domain.Member;
import Healthduo.demo.dto.MemberDTO;
import Healthduo.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
}
