package Healthduo.demo.service;

import Healthduo.demo.domain.MessageReceive;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MessageService {
    void messageSave(String receiveMemberId, String messageSendTitle, String messageSendContent, String loginMember);

    Page<MessageReceive> messageReceiveList(Pageable pageable, String loginMember);
}
