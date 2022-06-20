package Healthduo.demo.controller;


import Healthduo.demo.domain.Member;
import Healthduo.demo.dto.LoginDTO;
import Healthduo.demo.dto.MemberDTO;
import Healthduo.demo.service.RegionService;
import Healthduo.demo.web.Method;
import Healthduo.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class memberController {

    private final MemberService memberService;
    private final Method method;
    private final RegionService regionService;

    /**
     * 세션 유무에따라 홈화면 분류
     *
     * @param loginMember
     * @param model
     * @return
     */
    @GetMapping("/")
    public String homeLoginV3Spring(
            @SessionAttribute(name = "memberId", required = false)
                    Member loginMember,
            Model model) {
        //세션에 회원 데이터가 없으면 home
        if (loginMember == null) {
            return "HomeLogin";
        }
        //세션이 유지되면 로그인으로 이동
        List<String> regionInfo = regionService.getRegionInfo();
        model.addAttribute("member", loginMember);
        model.addAttribute("regionInfo",regionInfo);
        return "Home";
    }

    /**
     * 회원가입 창으로 이동
     *
     * @param model
     * @return
     */
    @GetMapping("/members/new")
    public String createForm(Model model) {
        MemberDTO memberDTO = new MemberDTO();
        model.addAttribute("memberDTO", memberDTO);
        return "members/createMemberForm";
    }

    /**
     * 회원가입
     *
     * @param memberDTO
     * @param result
     * @return
     */
    @PostMapping("/members/new")
    public String memberSave(@Valid MemberDTO memberDTO, BindingResult result) {
        Member member = new Member(memberDTO.getMemberId(), memberDTO.getMemberPassword(), memberDTO.getMemberSex(), memberDTO.getMemberEmail(), LocalDate.now(), memberDTO.getMemberPnumber());
        log.info(member.toString());

        //validation 검증
        if (result.hasErrors()) {
            return "members/createMemberForm";
        }
        log.info("memberSave(controller start)");
        memberService.memberSave(member);
        return "redirect:/";
    }

    /**
     * 로그인 화면 이동
     *
     * @param loginDTO
     * @param model
     * @return
     */

    @GetMapping("/login")
    public String getmemberLogin(LoginDTO loginDTO, Model model) {
        model.addAttribute("loginDTO", loginDTO);
        return "/members/login";
    }

    /**
     * 로그인 검증 로직
     *
     * @param loginDTO
     * @param result
     * @param response
     * @param request
     * @return
     */
    @PostMapping("/login")
    public String postmemberLogin(@Valid LoginDTO loginDTO, BindingResult result,
                                  HttpServletResponse response, HttpServletRequest request) {
        Member member = new Member(loginDTO.getMemberId(), loginDTO.getMemberPassword());
        log.info("check = {}", loginDTO.getIdRemember());
        //아이디 쿠키 생성
        if (loginDTO.getIdRemember()) {
            Cookie rememberId = new Cookie("rememberId", loginDTO.getMemberId());
            response.addCookie(rememberId);
        } else {
            Cookie deleteCookie = new Cookie("rememberId", null);
            deleteCookie.setMaxAge(0);
            response.addCookie(deleteCookie);
        }

        log.info("postmemberLogin(controller start");
        int resultfind = memberService.memberfind(member);
        log.info(String.valueOf(resultfind));

        switch (resultfind) {
            case 1:
                result.addError(new ObjectError("loginDTO", "등록된 아이디가 없습니다."));
                break;
            case 3:
                result.addError(new ObjectError("loginDTO", "등록된 비밀번호가 틀렸습니다."));
                break;
        }
        if (result.hasErrors()) {
            return "members/login";
        }
        HttpSession session = request.getSession();
        session.setAttribute("memberId", loginDTO.getMemberId());
        return "redirect:/";

    }

    /**
     * 로그아웃
     *
     * @param request
     * @return
     */
    @GetMapping("/logout")
    public String logoutV3(HttpServletRequest request) {
        //세션을 삭제한다.
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }
}
