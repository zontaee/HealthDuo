package Healthduo.demo.repository;

import Healthduo.demo.domain.Bbs;
import Healthduo.demo.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.Optional;


@SpringBootTest
@Transactional
class BbsRepositoryTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    BbsRepository bbsRepository;
    @Autowired
    EntityManager em;

    @Test
    @DisplayName("게시판 저장")
    void BbsSave(){
        Member member = new Member();
        member.setMemberId("아이디");
        Bbs bbs = new Bbs("제목 Test", "내용 Test", "2022-05-18",1,true,false,member);

        bbsRepository.BbsSave(bbs);
        Optional<Bbs> findbbs = bbsRepository.findById(1L);
        Assertions.assertThat(findbbs.get().getBbsTitle()).isEqualTo("제목 Test");
        Assertions.assertThat(findbbs.get().getBbsContent()).isEqualTo("내용 Test");
        System.out.println("----------------------------------------"+ findbbs.get().getCheckNS());


    }
    @Test
    @DisplayName("게시판 업데이트")
    void BbsUpdate(){
        Member member = new Member();
        member.setMemberId("아이디");
        Bbs bbs = new Bbs("제목 Test", "내용 Test", "2022-05-18",1,false,false,member);

        bbsRepository.BbsSave(bbs);

        bbs.setBbsTitle("변경 Test");
        bbs.setBbsContent("변경 Test");
        Optional<Bbs> updatebbs = bbsRepository.findById(1L);
        Assertions.assertThat(updatebbs.get().getBbsTitle()).isEqualTo("변경 Test");
        Assertions.assertThat(updatebbs.get().getBbsContent()).isEqualTo("변경 Test");
    }

}