package Healthduo.demo.controller;

import Healthduo.demo.dto.MessageSendDTO;
import Healthduo.demo.service.BbsService;
import Healthduo.demo.service.MessageService;
import Healthduo.demo.web.TransferDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MessageController {
    private final TransferDTO transferDTO;
    private final BbsService bbsService;
    private final MessageService messageService;

    @GetMapping("messagemenu")
    public String messageMenu() {
        log.info("messageMenu(controller start)");
        return "message/menu";
    }

    @GetMapping("messagesend")
    public String messageSend(Model model) {
        log.info("messageSend(controller start)");
        MessageSendDTO messageSendDTO = new MessageSendDTO();
        model.addAttribute("messageSendDTO", messageSendDTO);
        return "message/send";
    }

    @PostMapping("messagesave")
    public String messageSave(@RequestParam("receiveMemberId") String receiveMemberId,
                              @RequestParam("messageSendTitle") String messageSendTitle,
                              @RequestParam("messageSendContent") String messageSendContent,
                              @SessionAttribute(name = "memberId", required = false) String loginMember) {
        log.info("messagesave(controller start)");
        int resultMessageSave = messageService.messageSave(receiveMemberId, messageSendTitle, messageSendContent,loginMember);
        return "";

    }


}
