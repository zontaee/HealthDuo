package Healthduo.demo.controller;

import Healthduo.demo.service.BbsService;
import Healthduo.demo.service.MemberService;
import Healthduo.demo.web.TransferDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MessageController {
    private final TransferDTO transferDTO;
    private final BbsService bbsService;
    private final MemberService memberService;

}
