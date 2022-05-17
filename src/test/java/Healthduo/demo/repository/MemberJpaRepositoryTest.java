package Healthduo.demo.repository;


import Healthduo.demo.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberJpaRepositoryTest {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    EntityManager em;

    @Test
    void Membersave() {
        Member member = new Member();
        member.setMemberId("whdlsxo123");
        member.setMemberPassword("1234");
        member.setMemberSex("남");
        member.setMemberEmail("whdls123@naver.com");
        member.setMemberDate(LocalDate.now());
        member.setMemberPnumber("010-2222-3333");

        System.out.println(member);
        Member saveMember = memberRepository.saveMember(member);


        Member findmember = memberRepository.findById(saveMember.getMemberId()).get();
        assertThat(findmember.getMemberId()).isEqualTo(member.getMemberId());
    }

    @Test
    @DisplayName("중복 아이디 회원 검증")
    void overlapMemberSave() {

        Member member1 = new Member();
        member1.setMemberId("whdlsxo123");
        member1.setMemberPassword("1234");
        member1.setMemberSex("남");
        member1.setMemberEmail("whdls123@naver.com");
        member1.setMemberDate(LocalDate.now());
        member1.setMemberPnumber("010-2222-3333");

        em.persist(member1);
        em.flush();
        Member member2 = new Member();
        member2.setMemberId("whdlsxo123");
        member2.setMemberPassword("1234");
        member2.setMemberSex("남");
        member2.setMemberEmail("whdls123@naver.com");
        member2.setMemberDate(LocalDate.now());
        member2.setMemberPnumber("010-2222-3333");

        assertThatThrownBy(() -> memberRepository.saveMember(member2))
                .isInstanceOf(DataIntegrityViolationException.class);


    }

    @Test
    void Login() {
        Member member = new Member();
        member.setMemberId("whdlsxo123");
        member.setMemberPassword("1234");
        member.setMemberSex("남");
        member.setMemberEmail("whdls123@naver.com");
        member.setMemberDate(LocalDate.now());
        member.setMemberPnumber("010-2222-3333");

        System.out.println(member);
        Member saveMember = memberRepository.save(member);
        Member findmember = memberRepository.findById(saveMember.getMemberId()).get();

        assertThat(findmember.getMemberId()).isEqualTo("whdlsxo123");
        assertThat(findmember.getMemberPassword()).isEqualTo("1234");


    }
}