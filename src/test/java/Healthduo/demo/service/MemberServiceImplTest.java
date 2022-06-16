package Healthduo.demo.service;

import Healthduo.demo.domain.Bbs;
import Healthduo.demo.domain.Member;
import Healthduo.demo.repository.BbsRepository;
import Healthduo.demo.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
@Rollback(value = false)
class MemberServiceImplTest {
    @Autowired
    BbsRepository bbsRepository;
    @Autowired
    MemberRepository memberRepository;
    @Test
    void Bbssave() {
        Member member = new Member();
        member.setMemberId("whdlsxo123");
        member.setMemberPassword("1234");
        member.setMemberSex("남");
        member.setMemberEmail("whdls123@naver.com");
        member.setMemberDate(LocalDate.now());
        member.setMemberPnumber("010-2222-3333");

        System.out.println(member);
        Member saveMember = memberRepository.save(member);
        for (int i= 0; i<102;i++) {
            Bbs bbs = new Bbs("아뇽"+ i, "내용" + i, String.valueOf(LocalDate.now()), 0, false, false,saveMember);
            bbsRepository.save(bbs);
        }
    }

}