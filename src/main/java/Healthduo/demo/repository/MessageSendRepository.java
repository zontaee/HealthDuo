package Healthduo.demo.repository;

import Healthduo.demo.domain.MessageReceive;
import Healthduo.demo.domain.MessageSend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageSendRepository extends JpaRepository <MessageSend,Long>, MemberRepositoryCustom{
}
