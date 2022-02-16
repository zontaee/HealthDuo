package Healthduo.demo.controller;

import Healthduo.demo.domain.Bbs;
import Healthduo.demo.dto.BbsDTO;
import Healthduo.demo.repository.BbsRepository;
import Healthduo.demo.service.BbsService;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@ToString
public class BBsController {
    private final BbsService bbsService;


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
        bbs1.setBbs_date(String.valueOf(LocalDate.now()));
        bbs1.setBbs_hit(0);
        bbsService.bbsSave(bbs1);
        List<Bbs> bbs = bbsService.bbsList();
        List<BbsDTO> bbsDTO = new ArrayList<>();
        for (Bbs bbschange : bbs) {
            BbsDTO bbsDTOAdd = new BbsDTO(bbschange.getBbs_no(),bbschange.getBbs_title(),
                    bbschange.getBbs_content(),bbschange.getBbs_date(),bbschange.getBbs_hit(),
                    bbschange.getBbs_notice(),bbschange.getBbs_secret());
            bbsDTO.add(bbsDTOAdd);
        }
        log.info(String.valueOf(bbsDTO.get(0).getBbs_no()));
        log.info("bbsDTO size" + bbsDTO.size());
        model.addAttribute("bbsDTO",bbsDTO);
        return "bbs/BbsList";

    }


}
