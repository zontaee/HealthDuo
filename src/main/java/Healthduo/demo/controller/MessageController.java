package Healthduo.demo.controller;

import Healthduo.demo.dto.MessageReceiveDTO;
import Healthduo.demo.dto.MessageSendDTO;
import Healthduo.demo.service.BbsService;
import Healthduo.demo.service.MessageService;
import Healthduo.demo.web.TransferDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("messagereceived")
    public String messageReceived(@PageableDefault() Pageable pageable,
                                  @SessionAttribute(name = "memberId", required = false) String loginMember,
                                  Model model) {
        log.info("messagereceived(controller start)");
        Page<MessageReceiveDTO> messageReceiveDTO = transferDTO.messagePaging(pageable, loginMember);
        model.addAttribute("messageReceiveDTO", messageReceiveDTO);
        return "message/receivedlist";
    }
}
