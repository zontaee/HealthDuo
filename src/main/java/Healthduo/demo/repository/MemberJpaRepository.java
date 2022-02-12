package Healthduo.demo.repository;

import Healthduo.demo.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Transactional

@Slf4j
public class MemberJpaRepository {
    @PersistenceContext
    private EntityManager em;

    public Member save(Member member){
        em.persist(member);
        return member;
    }

    public Member find(Long member_number){
        return em.find(Member.class,member_number);
    }

}
