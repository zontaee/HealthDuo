package Healthduo.demo.controller;

import Healthduo.demo.service.BbsService;
import Healthduo.demo.service.MessageService;
import Healthduo.demo.web.TransferDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MessageRestController {
    private final TransferDTO transferDTO;
    private final BbsService bbsService;
    private final MessageService messageService;


    @PostMapping("messagesave")
    public String messageSave(@RequestParam("receiveMemberId") String receiveMemberId,
                              @RequestParam("messageSendTitle") String messageSendTitle,
                              @RequestParam("messageSendContent") String messageSendContent,
                              @SessionAttribute(name = "memberId", required = false) String loginMember) {
        log.info("messagesave(controller start)");
        String message = "";
        try {
            messageService.messageSave(receiveMemberId, messageSendTitle, messageSendContent,loginMember);
        }catch (Exception e){
           log.info("message={}" ,e.getMessage());
           message = e.getMessage();
        }

        return message;

    }
}
