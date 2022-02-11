package Healthduo.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemberController {

    @RequestMapping("/")
    public String Home(){
        return "Home";
    }

    @GetMapping("/members/new")
    public String createForm(Model model) {
        return "members/createMemberForm";
    }
}
