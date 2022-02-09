package Healthduo.demo.repository;

import Healthduo.demo.domain.Member;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Repository
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
