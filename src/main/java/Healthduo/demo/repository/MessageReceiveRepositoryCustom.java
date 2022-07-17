package Healthduo.demo.repository;

import Healthduo.demo.domain.MessageReceive;
import Healthduo.demo.domain.MessageSend;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MessageReceiveRepositoryCustom {
    Page<MessageReceive> findMessageReceiveList(String loginMember, Pageable pageable);

    Page<MessageSend> findMessageSendList(String loginMember, Pageable pageable);
}
