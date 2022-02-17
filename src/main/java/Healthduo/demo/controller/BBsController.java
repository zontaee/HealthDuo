package Healthduo.demo.controller;

import Healthduo.demo.domain.Bbs;
import Healthduo.demo.dto.BbsDTO;
import Healthduo.demo.method.Method;
import Healthduo.demo.service.BbsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BBsController {
    private final Method method;
    private final BbsService bbsService;


    @GetMapping("/bbsLists")
    public String BbsList(@PageableDefault(sort = "bbs_no", direction = Sort.Direction.DESC) Pageable pageable , Model model) throws Exception {
        Page<Bbs> bbsList = bbsService.bbsList(pageable);
        model.addAttribute("bbsList",bbsList);
        log.info("총 element 수 : {}, 전체 page 수 : {}, 페이지에 표시할 element 수 : {}, 현재 페이지 index : {}, 현재 페이지의 element 수 : {}",
                bbsList.getTotalElements(), bbsList.getTotalPages(), bbsList.getSize(),
                bbsList.getNumber(), bbsList.getNumberOfElements());
        return"bbs/bbsList";
    }
    @RequestMapping("/write")
    public String BbsWrite(Model model){
        log.info("BbsWrite(controller start");
        BbsDTO bbsDTO = new BbsDTO();
        model.addAttribute("bbsDTO",bbsDTO);
        return "bbs/write";
    }
    @PostMapping("/bbsSave")
    public String BbsSave(Bbs bbs1, Model model){
        log.info("bbsSave(controller start");
        log.info("bbsList(controller start");
        log.info("bbs1 = " + bbs1.toString());
        bbs1.setBbsDate(String.valueOf(LocalDate.now()));
        bbs1.setBbsHit(0);
        bbsService.bbsSave(bbs1);
        /*List<BbsDTO> bbsDTO = method.BbsListPaging();
        model.addAttribute("bbsDTO",bbsDTO);*/
        return "bbsList";

    }



}
