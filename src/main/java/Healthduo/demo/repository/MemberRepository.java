package Healthduo.demo.repository;

import Healthduo.demo.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

public interface MemberRepository extends JpaRepository <Member,String>, MemberRepositoryCustom{
}
