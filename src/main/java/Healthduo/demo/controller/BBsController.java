package Healthduo.demo.controller;

import Healthduo.demo.domain.Bbs;
import Healthduo.demo.domain.Member;
import Healthduo.demo.dto.BbsDTO;
import Healthduo.demo.service.MemberService;
import Healthduo.demo.web.Method;
import Healthduo.demo.service.BbsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BBsController {
    private final Method method;
    private final BbsService bbsService;
    private final MemberService memberService;


    @GetMapping("/bbsLists")
    public String BbsList(@PageableDefault() Pageable pageable , Model model) throws Exception {
        Page<BbsDTO> bbsDTO = method.BbsListPaging(pageable);
        model.addAttribute("bbsDTO",bbsDTO);
        return"bbs/bbsList";
    }
    @RequestMapping("/write")
    public String BbsWrite(Model model){
        log.info("BbsWrite(controller start)");
        BbsDTO bbsDTO = new BbsDTO();
        model.addAttribute("bbsDTO",bbsDTO);
        return "bbs/write";
    }
    @PostMapping("/bbsSave")
    public String BbsSave(Bbs bbs, Pageable pageable , Model model,
                          @SessionAttribute(name = "memberId", required = false)
            String loginMember) throws Exception {
        log.info("bbsSave(controller start)");
        log.info("memberfindById(controller start)");
        Member member = memberService.memberfindById(loginMember);
        bbs.setMember(member);
        bbs.setBbsDate(String.valueOf(LocalDate.now()));
        bbs.setBbsHit(0);
        bbsService.bbsSave(bbs);
        log.info("bbsList(controller start)");
        Page<BbsDTO> bbsDTO = method.BbsListPaging(pageable);
        model.addAttribute("bbsDTO",bbsDTO);
        return "bbs/bbsList";
    }
    @GetMapping("/content/{bbsNo}")
    public String BbsContent(@PathVariable Long bbsNo, Model model){
        log.info("BbsContent(controller start)");
        Optional<Bbs> bbs = bbsService.findContent(bbsNo);
        BbsDTO bbsDTO = method.getBbsDTO(bbs);
        model.addAttribute("bbsDTO",bbsDTO);
        log.info("bbs_hit = " + bbsDTO.getBbsHit());
        return "bbs/content";
    }
    @GetMapping("/updateForm/{bbsNo}")
    public String findContentUpdate(@PathVariable Long bbsNo, Model model){
        log.info("BbsContent(controller start)");
        Optional<Bbs> bbs = bbsService.findContentUpdate(bbsNo);
        BbsDTO bbsDTO = method.getBbsDTO(bbs);
        model.addAttribute("bbsDTO",bbsDTO);
        return "bbs/updateForm";
    }



    @PostMapping("/bbsUpdate")
    public String ContentUpdate(Bbs bbs, Model model){
        log.info("ContentUpdate(controller start)");
        Optional<Bbs> bbsUpdate = bbsService. ContentUpdate(bbs);
        BbsDTO bbsDTO = method.getBbsDTO(bbsUpdate);
        model.addAttribute("bbsDTO",bbsDTO);
        return "bbs/content";

    }
    @GetMapping("/delete/{bbsNo}")
    private String deleteContent(@PathVariable Long bbsNo,Pageable pageable,Model model) throws Exception {
        log.info("deleteContent(controller start)");
        bbsService.deleteContent(bbsNo);
        Page<BbsDTO> bbsDTO = method.BbsListPaging(pageable);
        model.addAttribute("bbsDTO",bbsDTO);
        return "bbs/bbsList";
    }


}
