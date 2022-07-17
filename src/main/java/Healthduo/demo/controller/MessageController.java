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
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("messagereceivedlist")
    public String messageReceivedList(@PageableDefault() Pageable pageable,
                                      @SessionAttribute(name = "memberId", required = false) String loginMember,
                                      Model model) {
        log.info("messagereceivedlist(controller start)");
        Page<MessageReceiveDTO> messageReceiveDTO = transferDTO.messageReceivedPaging(pageable, loginMember);
        model.addAttribute("messageReceiveDTO", messageReceiveDTO);
        return "message/receivedlist";
    }

    @GetMapping("/messagereceivedcontent/{messageReceiveNo}")
    public String messageReceivedContent(@PathVariable Long messageReceiveNo, Model model) {
        log.info("messageReceivedContent(controller start)");

        MessageReceiveDTO messageReceiveDTO = transferDTO.messageReceivedContent(messageReceiveNo);
        model.addAttribute("messageReceiveDTO", messageReceiveDTO);
        return "message/receivedContent";
    }

    @GetMapping("messagesendlist")
    public String messageSendList(@PageableDefault() Pageable pageable,
                                  @SessionAttribute(name = "memberId", required = false) String loginMember,
                                  Model model) {
        log.info("messageSendList(controller start)");
        Page<MessageSendDTO> messageSendDTO = transferDTO.messageSendPaging(pageable, loginMember);
        model.addAttribute("messageSendDTO", messageSendDTO);
        return "message/sendlist";
    }
    @GetMapping("/messagesendcontent/{messageSendNo}")
    public String messageSendContent(@PathVariable Long messageSendNo, Model model) {
        log.info("messageReceivedContent(controller start)");

        MessageSendDTO messageSendDTO = transferDTO.messageSendContent(messageSendNo);
        model.addAttribute("messageSendDTO", messageSendDTO);
        return "message/sendContent";
    }

}
