package Healthduo.demo.repository;

import Healthduo.demo.domain.MessageReceive;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageReceiveRepository extends JpaRepository <MessageReceive,Long>, MemberRepositoryCustom{
}
