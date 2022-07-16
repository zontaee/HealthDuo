package Healthduo.demo.service;

import Healthduo.demo.domain.MessageReceive;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface MessageService {
    void messageSave(String receiveMemberId, String messageSendTitle, String messageSendContent, String loginMember);

    Page<MessageReceive> messageReceiveList(Pageable pageable, String loginMember);

    Optional<MessageReceive> messageReceivedContent(Long messageReceiveNo);
}
