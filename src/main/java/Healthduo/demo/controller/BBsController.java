package Healthduo.demo.controller;

import Healthduo.demo.dto.BbsDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BBsController {

    @GetMapping("/write")
    public String BbsWrite(BbsDTO bbsDTO ,Model model){
        model.addAttribute("bbsDTO",bbsDTO);
        return "bbs/write";
    }

}
