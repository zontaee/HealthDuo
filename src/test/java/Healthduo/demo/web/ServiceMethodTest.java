package Healthduo.demo.web;

import Healthduo.demo.domain.Bbs;
import Healthduo.demo.domain.Comment;
import Healthduo.demo.domain.Member;
import Healthduo.demo.domain.Region;
import Healthduo.demo.repository.BbsRepository;
import Healthduo.demo.repository.CommentRepository;
import Healthduo.demo.repository.MemberRepository;
import Healthduo.demo.repository.RegionRepository;
import Healthduo.demo.service.CommentRestService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
@Rollback(value = false)
class ServiceMethodTest {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    BbsRepository bbsRepository;
    @Autowired
    EntityManager em;
    @Autowired
    ServiceMethod serviceMethod;
    @Autowired
    RegionRepository regionRepository;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    CommentRestService commentRestService;

    @Test
    void bbsLnitialization() {
        Member member = new Member();
        member.setMemberId("아이디");
        Bbs bbs = new Bbs("제목 Test", "내용 Test", "2022-05-18", 1, true, false, member);
        serviceMethod.bbsLnitialization(bbs, "분당동", member);
        assertThat(bbs.getBbsHit()).isEqualTo(0);
        assertThat(bbs.getCheckNS()).isEqualTo(" n");
    }

    @Test
    void increaseHit() {
        Member member = new Member();
        member.setMemberId("아이디");
        Bbs bbs = new Bbs("제목 Test", "내용 Test", "2022-05-18", 1, true, false, member);
        serviceMethod.increaseHit(Optional.of(bbs));
        assertThat(bbs.getBbsHit()).isEqualTo(2);

    }

    @Test
    void loginCheck() {
        Member member = new Member();
        member.setMemberId("아이디");
        member.setMemberPassword("1234");
        Member loginCheck = new Member();
        loginCheck.setMemberId("아이디");
        loginCheck.setMemberPassword("1234");
        int checkNumber = serviceMethod.loginCheck(member, loginCheck);
        assertThat(checkNumber).isEqualTo(2);


    }

    @Test
    void checkIdNumber() {
        Member member = new Member();
        member.setMemberId("아이디");
        int checkIdNumber = serviceMethod.CheckIdNumber(member);
        assertThat(checkIdNumber).isEqualTo(1);
    }

    @Test
    void distinguishRegion() {
        ArrayList<Region> addRegion = new ArrayList<>();
        addRegion.add(new Region("경기도", "안양시", "안양구", "평촌동"));
        addRegion.add(new Region("서울특별시", "강남구", "서초동"));
        for (int i = 0; i < addRegion.size(); i++) {
            regionRepository.save(addRegion.get(i));
        }
        List<String> result = serviceMethod.distinguishRegion("서울특별시");
        assertThat(result.get(0)).isEqualTo("강남구");

    }

    @Test
    void incrementGroup() {

        Integer commentCnt = serviceMethod.incrementGroup();
        assertThat(commentCnt).isEqualTo(0);
    }

    @Test
    void incrementCnt() {
        Integer commentCnt = serviceMethod.incrementCnt();
        assertThat(commentCnt).isEqualTo(0);
    }

/*  트랜잭션 공부 후 다시
    @Test
    void saveComment() {
        Member member = new Member();
        member.setMemberId("아이디");
        Bbs bbs = new Bbs("제목 Test", "내용 Test", "2022-05-18",1,true,false,member);
        commentRestService.CommentSave("test",bbs,member);
       Optional<Comment> comment = commentRepository.findById(1L);
        assertThat(comment.get().getContent()).isEqualTo("test");
    }
*/

    @Test
    void incrementLevel() {
        String[] sliceChildInfo = null;
        Integer commentCnt = serviceMethod.incrementLevel(sliceChildInfo);
        assertThat(commentCnt).isEqualTo(0);
    }

    @Test
    void fixedLevel() {
        String[] sliceChildInfo = null;
        Integer commentCnt = serviceMethod.fixedLevel(sliceChildInfo);
        assertThat(commentCnt).isEqualTo(0);
    }

}