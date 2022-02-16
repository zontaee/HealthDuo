package Healthduo.demo.repository;

import Healthduo.demo.domain.Member;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;

@RequiredArgsConstructor
public class MemberRepositoryCustomImpl implements MemberRepositoryCustom{

    private  final EntityManager em;
    public Member saveMember(Member member){
        em.persist(member);
        return member;
    }

}
