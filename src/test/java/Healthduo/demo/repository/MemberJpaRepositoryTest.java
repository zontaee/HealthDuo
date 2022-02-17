package Healthduo.demo.repository;


import Healthduo.demo.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@SpringBootTest
@Transactional
@Rollback(value = false)
class MemberJpaRepositoryTest {
    @Autowired
    MemberRepository memberRepository;
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
        Member saveMember = memberRepository.save(member);


        Member findmember = memberRepository.findById(saveMember.getMemberId()).get();
        Assertions.assertThat(findmember.getMemberId()).isEqualTo(member.getMemberId());
    }
    @Test
    void Login(){
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
        
        Assertions.assertThat(findmember.getMemberId()).isEqualTo("whdlsxo123");
        Assertions.assertThat(findmember.getMemberPassword()).isEqualTo("1234");


    }
}