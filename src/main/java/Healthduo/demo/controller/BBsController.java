package Healthduo.demo.controller;

import Healthduo.demo.domain.Bbs;
import Healthduo.demo.domain.Member;
import Healthduo.demo.dto.BbsDTO;
import Healthduo.demo.service.MemberService;
import Healthduo.demo.service.RegionService;
import Healthduo.demo.web.TransferDTO;
import Healthduo.demo.service.BbsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BBsController {
    private final TransferDTO transferDTO;
    private final BbsService bbsService;
    private final MemberService memberService;
    private final RegionService regionService;

    /**
     * 게시판 글 목록(페이징)
     *
     * @param pageable
     * @param address
     * @param model
     * @return
     * @throws Exception
     */
    @GetMapping("/bbsLists")
    public String BbsList(@PageableDefault() Pageable pageable,
                          @RequestParam("address") String address,
                          Model model){
        log.info("bbsLists(controller start)");
        Page<BbsDTO> bbsDTO = transferDTO.BbsListPaging(pageable, address);
        List<BbsDTO> noticeBbs = transferDTO.getBbsDTO();
        model.addAttribute("address",address);
        model.addAttribute("bbsDTO", bbsDTO);
        model.addAttribute("noticeBbs", noticeBbs);
        return "bbs/bbsList";
    }

    /**
     * 게시판 검색 결과
     *
     * @param pageable
     * @param model
     * @param bbsListSearch
     * @param searchText
     * @return
     * @throws Exception
     */
    @RequestMapping("bbsListSearch")
    public String bbsListSearch(@PageableDefault() Pageable pageable, Model model,
                                @RequestParam("searchField") String bbsListSearch,
                                @RequestParam("searchText") String searchText,
                                @RequestParam("address") String address) {
        log.info("bbsListSearch(controller start)");
        Page<BbsDTO> bbsDTO = transferDTO.BbsListSerchPaging(pageable, bbsListSearch, searchText);
        model.addAttribute("bbsDTO", bbsDTO);
        model.addAttribute("searchField", bbsListSearch);
        model.addAttribute("searchText", searchText);
        model.addAttribute("address",address);
        return "bbs/bbsListSearch";
    }

    /**
     * 글작성
     *
     * @param model
     * @return
     */
    @GetMapping("/write")
    public String BbsWrite(@RequestParam("address") String address,Model model) {
        log.info("BbsWrite(controller start)");
        BbsDTO bbsDTO = new BbsDTO();
        model.addAttribute("bbsDTO", bbsDTO);
        model.addAttribute("address",address);
        return "bbs/write";
    }

    /**
     * 글 저장(서버)
     *
     * @param bbs
     * @param loginMember
     * @return
     * @throws Exception
     */
    @PostMapping("/bbsSave")
    public String BbsSave(Bbs bbs,
                          @RequestParam("address") String address,
                          RedirectAttributes redirectAttributes,
                          @SessionAttribute(name = "memberId", required = false)
                                  String loginMember) {
        log.info("bbsSave(controller start)");
        Member member = memberService.memberfindById(loginMember);
        redirectAttributes.addAttribute("address", address);
        bbsService.bbsSave(bbs, address, member);
        return "redirect:/bbsLists";
    }

    /**
     * 게시글 보기
     *
     * @param bbsNo
     * @param model
     * @return
     */
    @GetMapping("/content/{bbsNo}")
    public String BbsContent(@PathVariable Long bbsNo, Model model) {
        log.info("BbsContent(controller start)");
        Optional<Bbs> bbs = bbsService.findContent(bbsNo);
        BbsDTO bbsDTO = transferDTO.getBbsDTO(bbs);
        model.addAttribute("bbsDTO", bbsDTO);
        log.info("bbs_hit = " + bbsDTO.getBbsHit());
        return "bbs/content";
    }

    /**
     * 게시글 수정
     *
     * @param bbsNo
     * @param model
     * @return
     */
    @GetMapping("/updateForm/{bbsNo}")
    public String findUpdatedContent(@PathVariable Long bbsNo, Model model) {
        log.info("findUpdatedContent(controller start)");
        Optional<Bbs> bbs = bbsService.findUpdatingContent(bbsNo);
        BbsDTO bbsDTO = transferDTO.getBbsDTO(bbs);
        model.addAttribute("bbsDTO", bbsDTO);
        return "bbs/updateForm";
    }

    /**
     * 게시글 업데이드(서버)
     *
     * @param bbs
     * @param model
     * @return
     */

    @PostMapping("/bbsUpdate")
    public String ContentUpdate(Bbs bbs, Model model) {
        log.info("ContentUpdate(controller start)");
        Optional<Bbs> bbsUpdate = bbsService.ContentUpdate(bbs);
        BbsDTO bbsDTO = transferDTO.getBbsDTO(bbsUpdate);
        model.addAttribute("bbsDTO", bbsDTO);
        return "bbs/content";

    }

    /**
     * 글삭제
     *
     * @param bbsNo
     * @param pageable
     * @param model
     * @return
     * @throws Exception
     */
    @GetMapping("/delete/{bbsNo}")
    private String deleteContent(@PathVariable Long bbsNo, Pageable pageable, Model model) throws Exception {
        log.info("deleteContent(controller start)");
        bbsService.deleteContent(bbsNo);
        Page<BbsDTO> bbsDTO = transferDTO.BbsListPaging(pageable);
        model.addAttribute("bbsDTO", bbsDTO);
        return "bbs/bbsList";
    }


}
