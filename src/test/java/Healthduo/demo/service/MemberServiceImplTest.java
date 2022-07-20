package Healthduo.demo.service;

import Healthduo.demo.domain.Bbs;
import Healthduo.demo.domain.Member;
import Healthduo.demo.repository.BbsRepository;
import Healthduo.demo.repository.MemberRepository;
import Healthduo.demo.web.ServiceMethod;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@Transactional
@Rollback(value = false)
class MemberServiceImplTest {
    @Autowired
    BbsRepository bbsRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    EntityManager em;
    @Autowired
    ServiceMethod serviceMethod;
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
    @Test
    void JpaNPlusOneProblem(){
        Member member1 = new Member("1", "123", "남자", "whdlsxo123@naver.com", LocalDate.now(), "010-2323-4343");
        Member member2 = new Member("2", "123", "남자", "whdlsxo123@naver.com", LocalDate.now(), "010-2323-4343");
        Member member3 = new Member("3", "123", "남자", "whdlsxo123@naver.com", LocalDate.now(), "010-2323-4343");
        Member member4 = new Member("4", "123", "남자", "whdlsxo123@naver.com", LocalDate.now(), "010-2323-4343");
        Member saveMember1 = memberRepository.save(member1);
        for (int i= 0; i<30;i++) {
            Bbs bbs = new Bbs("1+"+ i, "1+" + i, String.valueOf(LocalDate.now()), 0, false, false,saveMember1);
            serviceMethod.bbsSetting(bbs,"우리집",saveMember1);
        }
        Member saveMember2 = memberRepository.save(member2);
        for (int i= 0; i<30;i++) {
            Bbs bbs = new Bbs("1+"+ i, "1+" + i, String.valueOf(LocalDate.now()), 0, false, false,saveMember2);
            serviceMethod.bbsSetting(bbs,"우리집",saveMember2);
        }
        Member saveMember3 = memberRepository.save(member3);
        for (int i= 0; i<30;i++) {
            Bbs bbs = new Bbs("1+"+ i, "1+" + i, String.valueOf(LocalDate.now()), 0, false, false,saveMember3);
            serviceMethod.bbsSetting(bbs,"우리집",saveMember3);
        }
        Member saveMember4 = memberRepository.save(member4);
        for (int i= 0; i<30;i++) {
            Bbs bbs = new Bbs("1+"+ i, "1+" + i, String.valueOf(LocalDate.now()), 0, false, false,saveMember4);
            serviceMethod.bbsSetting(bbs,"우리집",saveMember4);
        }
        System.out.println("======================================================================================================");
        List<Member> all = memberRepository.findAll();
        for (Member member : all) {
            System.out.println(member.getBbs().get(1).getBbsContent());
        }

    }

}