package Healthduo.demo.web;

import Healthduo.demo.domain.*;
import Healthduo.demo.repository.*;
import Healthduo.demo.service.CommentRestService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = true)
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
    @Autowired
    MessageSendRepository messageSendRepository;
    @Autowired
    MessageReceiveRepository messageReceiveRepository;

    @Test
    void bbsLnitialization() {
        Member member = new Member();
        member.setMemberId("아이디");
        Bbs bbs = new Bbs("제목 Test", "내용 Test", "2022-05-18", 1, true, false, member);
        serviceMethod.bbsSetting(bbs, "분당동", member);
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


    @Test
    void saveComment() {
        Member member = new Member();
        member.setMemberId("아이디");
        memberRepository.save(member);
        Bbs bbs = new Bbs("제목 Test", "내용 Test", "2022-05-18", 1, true, false, member);
        serviceMethod.bbsSetting(bbs,"분당동",member);
        commentRestService.CommentSave("test", bbs, "아이디");
        Optional<Comment> comment = commentRepository.findById(1L);
        assertThat(comment.get().getContent()).isEqualTo("test");
    }

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

    @Test
    void CheckError() {
        String errorMessage = "";
        //아이디 입력하지 않았거나 다를 경우
        try {
            Optional<Member> findReciveMemberId = Optional.empty();
            serviceMethod.CheckError("title", "content", findReciveMemberId);
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }
        assertThat(errorMessage).isEqualTo("받는 사람 아이디가 존재하지 않습니다.");
        //쪽지 제목을 입력하지 않았을 경우
        try {
            Member member = new Member();
            member.setMemberId("아이디");
            Optional<Member> findReciveMemberId = Optional.of(member);
            serviceMethod.CheckError("", "content", findReciveMemberId);
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }
        assertThat(errorMessage).isEqualTo("쪽지 제목을 입력해주세요.");
        //쪽지 내용을 입력하지 않았을 경우
        try {
            Member member = new Member();

            Optional<Member> findReciveMemberId = Optional.of(member);
            serviceMethod.CheckError("title", "", findReciveMemberId);
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }
        assertThat(errorMessage).isEqualTo("쪽지 내용을 입력해주세요.");
    }

    @Test
    void messageSendSave() {
        Member member = new Member("sendId", "123", "남자", "whdlsxo123@naver.com", LocalDate.now(), "010-2323-4343");
        Optional<Member> sendMemberInfo = Optional.of(member);
        memberRepository.saveMember(member);
        serviceMethod.messageSendSave("messageSendTitle", "receiveId", "content", "loginId", sendMemberInfo);
        Optional<MessageSend> findMessageSend = messageSendRepository.findById(1L);
        assertThat(findMessageSend.get().getMessageSendTitle()).isEqualTo("messageSendTitle");
    }

    @Test
    void messageReceiveSave() {
        Member member = new Member("sendId", "123", "남자", "whdlsxo123@naver.com", LocalDate.now(), "010-2323-4343");
        Optional<Member> sendMemberInfo = Optional.of(member);
        memberRepository.saveMember(member);
        serviceMethod.messageReceivedSave("messageReceiveTitle", "receiveId", "content", "loginId", sendMemberInfo);
        Optional<MessageReceive> findMessageReceice = messageReceiveRepository.findById(1L);
        assertThat(findMessageReceice.get().getMessageReceiveTitle()).isEqualTo("messageReceiveTitle");
    }


}