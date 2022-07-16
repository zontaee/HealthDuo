package Healthduo.demo.repository;

import Healthduo.demo.domain.MessageReceive;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MessageReceiveRepositoryCustom {
    Page<MessageReceive> findMessageReceive(String loginMember, Pageable pageable);
}
