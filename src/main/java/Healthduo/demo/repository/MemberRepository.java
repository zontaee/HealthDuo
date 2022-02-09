package Healthduo.demo.repository;

import Healthduo.demo.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MemberRepository extends JpaRepository <Member,Long> {
}
