package Healthduo.demo.repository;


import Healthduo.demo.domain.Member;
import Healthduo.demo.repository.MemberJpaRepository;
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
        member.setMember_id("whdlsxo123");
        member.setMember_password("1234");
        member.setMember_sex("남");
        member.setMember_email("whdls123@naver.com");
        member.setMember_date(LocalDate.now());
        member.setMember_pnumber("010-2222-3333");

        System.out.println(member);
        Member saveMember = memberRepository.save(member);

        Member findmember = memberRepository.findById(saveMember.getMember_number()).get();
        Assertions.assertThat(findmember.getMember_id()).isEqualTo(member.getMember_id());
    }
}