package Healthduo.demo.controller;


import Healthduo.demo.domain.Member;
import Healthduo.demo.dto.BbsDTO;
import Healthduo.demo.dto.LoginDTO;
import Healthduo.demo.dto.MemberDTO;
import Healthduo.demo.service.RegionService;
import Healthduo.demo.service.MemberService;
import Healthduo.demo.web.ControllerMethod;
import Healthduo.demo.web.TransferDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

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
    private final RegionService regionService;
    private final ControllerMethod controllerMethod;
    private final TransferDTO transferDTO;

    /**
     * 세션 유무에따라 홈화면 분류
     *
     * @param loginMember
     * @param model
     * @return
     */
    @GetMapping("/")
    public String homeLoginCheck(
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
        model.addAttribute("regionInfo", regionInfo);
        return "Home";
    }

    @GetMapping("/member/checkinfomember")
    public String CheckInfoMember() {
        return "members/CheckInfoMember";
    }

    /**
     * 회원가입 창으로 이동
     *
     * @param model
     * @return
     */
    @GetMapping("/members/new")
    public String createMemberForm(Model model) {
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
        Member member = transferDTO.getMember(memberDTO);
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
    public String memberLogin(LoginDTO loginDTO, Model model) {
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
    public String memberLogin(@Valid LoginDTO loginDTO, BindingResult result,
                              HttpServletResponse response, HttpServletRequest request) {
        Member member = new Member(loginDTO.getMemberId(), loginDTO.getMemberPassword());
        log.info("memberLogin(controller start");

        controllerMethod.createCookie(loginDTO, response);
        int loginCheck = memberService.loginCheck(member);
        controllerMethod.addErrorMessage(result, loginCheck);
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
    public String logout(HttpServletRequest request) {
        //세션을 삭제한다.
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }

    /**
     * my page 이동
     *
     * @return
     */
    @GetMapping("/mypage")
    public String myPage() {
        return "members/mypage";
    }

    @GetMapping("/member/changeinfomember")
    public String changeInfoMember(@SessionAttribute(name = "memberId", required = false)
                                               String loginMember,
                                               Model model) {
        Member member = memberService.memberfindById(loginMember);
        model.addAttribute("member",member);
        return "members/changeinfo";
    }

    /**
     * 회원이 작성한 글 목록
     * @param loginMember
     * @param pageable
     * @param model
     * @return
     */
    @GetMapping("/member/bbsinfomember")
    public String bbsInfoMember(@SessionAttribute(name = "memberId", required = false) String loginMember,
                                @PageableDefault() Pageable pageable,
                                Model model) {
        log.info("bbsInfoMember(controller start)");
        Page<BbsDTO> bbsDTO = transferDTO.BbsListSerchPaging(pageable,"userID",loginMember);
        model.addAttribute("bbsDTO", bbsDTO);
        model.addAttribute("searchField", "userID");
        model.addAttribute("searchText", loginMember);
        return "members/bbsinfomember";
    }

}
