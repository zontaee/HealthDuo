package Healthduo.demo.web;

import Healthduo.demo.domain.*;
import Healthduo.demo.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class ServiceMethod {
    private final BbsRepository bbsRepository;
    private final RegionRepository regionRepository;
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final MessageReceiveRepository messageReceiveRepository;
    private final MessageSendRepository messageSendRepository;

    public Pageable getPageableBbs(Pageable pageable) {
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "bbsNo"));
        return pageable;
    }

    public Page<Bbs> resultBySearch(Pageable pageable, String bbsListSearch, String searchText) {
                Page<Bbs> searchedBbsList = null;
        switch (bbsListSearch) {
            case "bbsTitle":
                searchedBbsList = bbsRepository.findBySearchTitle(searchText, pageable);
                break;
            case "bbsContent":
                searchedBbsList = bbsRepository.findBySearchContent(searchText, pageable);
                break;
            case "userID":
                searchedBbsList = bbsRepository.findBySearchUserID(searchText, pageable);
                break;
        }
        return searchedBbsList;
    }

    public void bbsSetting(Bbs bbs, String street, Member member) {
        bbs.setMember(member);
        bbs.setAddress(street);
        bbs.setBbsDate(String.valueOf(LocalDate.now()));
        bbs.setBbsHit(0);
        bbs.setCheckNS(" ");
        if (bbs.getBbsNotice()) {
            bbs.noticeAddCheck();
        }
        if (bbs.getBbsSecret()) {
            bbs.secretAddCheck();

        }
        bbs.addMember(member);

    }

    public void increaseHit(Optional<Bbs> bbsContent) {
        bbsContent.get().setBbsHit(bbsContent.get().getBbsHit() + 1);
    }

    public int loginCheck(Member member, Member loginCheck) {
        int loginCheckResult;
        if (loginCheck == null) {
            loginCheckResult = 1;
            return loginCheckResult;
        } else {
            if (loginCheck.getMemberPassword().equals(member.getMemberPassword())) {
                loginCheckResult = 2;
                return loginCheckResult;
            } else {
                loginCheckResult = 3;
                return loginCheckResult;
            }
        }
    }
    //loginCheck = 1 ????????? ???????????? ????????????.
    //loginCheck = 2 ????????? ???????????? ?????? ??????
    //loginCheck = 3 ????????? ??????????????? ???????????????.


    public int CheckIdNumber(Member findId) {
        int checkIdNumber;
        if (findId == null) {
            checkIdNumber = 0; //????????????x
        } else {
            checkIdNumber = 1; //????????????o
        }
        return checkIdNumber;
    }

    /**
     * ex ???????????? ????????? ?????????????????? ????????? ?????? ?????? ?????????????????? ??????????????? ???????????? ????????????.
     * @param region
     * @return
     */
    public List<String> distinguishRegion(String region) {
        if (region.equals("???????????????")) {
            List<String> fullCity = regionRepository.findFullCity(region);
            return fullCity;
        } else {
            List<String> citys = regionRepository.findCity(region);
            return citys;
        }
    }

    public Integer incrementGroup() {
        Integer commentGroup;
        if (commentRepository.findCommentGroup().equals(Optional.empty())) {
            commentGroup = 0;
        } else {
            commentGroup = commentRepository.findCommentGroup().get() + 1;
        }
        return commentGroup;
    }

    public Integer incrementCnt() {
        Integer commentCnt;
        if (commentRepository.findCommentCnt().equals(Optional.empty())) {
            commentCnt = 0;
        } else {
            commentCnt = commentRepository.findCommentCnt().get() + 1;
        }
        return commentCnt;
    }
    public void saveComment(String content, Bbs bbs, Member member, Integer commentGroup, Integer commentCnt) {
        Comment comment = new Comment(content, commentCnt, commentGroup, String.valueOf(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
        comment.addBbs(bbs);
        comment.addMember(member);
        comment.setCheckInfo(1);
        commentRepository.contentSave(comment);
        comment.addMember(member);
        comment.addBbs(bbs);
    }
    public Integer incrementLevel(String[] sliceChildInfo) {
        Integer level;
        if (commentRepository.findLevel().equals(Optional.empty())) {
            level = 0;
        } else {
            level = Integer.parseInt(sliceChildInfo[2]) + 1;
        }
        return level;
    }
    public Integer fixedLevel(String[] sliceChildInfo) {
        Integer level;
        if (commentRepository.findLevel().equals(Optional.empty())) {
            level = 0;
        } else {
            level = Integer.parseInt(sliceChildInfo[2]);
        }
        return level;
    }
    public Integer sortLogic(int seq, String[] sliceChildInfo, Integer commentGroupNubmer) {
        Integer commentSequence;

        Integer sameLevelAndGroupMaxSeq = commentRepository.findSameLevelAndGroupMaxSeq(commentGroupNubmer, Integer.parseInt(sliceChildInfo[2])+1); //?????? ????????? ????????????(+1)??? ?????? ?????? seq?????? ????????????

        if(sameLevelAndGroupMaxSeq == null){ //?????? ????????? ?????? ???????????? ????????????
            commentSequence = seq + 1;
            commentRepository.updateAllSequence(seq);
        }else {  //??????????????? ?????? ???????????? ????????? ????????????
            if(seq <= sameLevelAndGroupMaxSeq){  //????????? ????????? ????????? ?????? ?????????????????? ????????? ???????????? ???????????? ????????? ?????? ????????? ??????????????? ?????? ???????????? ????????????.
                commentSequence = sameLevelAndGroupMaxSeq +1;
                commentRepository.updateAllSequence(sameLevelAndGroupMaxSeq);
            }else {
                commentSequence = seq + 1;
                commentRepository.updateAllSequence(seq);
            }
        }
        return commentSequence;
    }
    public int getDeleteCheckNumber(String memberId) {
        int deleteCheckNumber = 1;
        try {
            memberRepository.deleteById(memberId);
        } catch (Exception e) {
            deleteCheckNumber = 0;
        }
        return deleteCheckNumber;
    }

    /**
     * ????????? ?????? ?????? ?????????
     * @param messageSendTitle
     * @param messageSendContent
     * @param findReciveMemberId
     */
    public void CheckError(String messageSendTitle, String messageSendContent, Optional<Member> findReciveMemberId) {
        if(findReciveMemberId.isEmpty()){
            throw new RuntimeException("?????? ?????? ???????????? ???????????? ????????????.");
        }
        if(messageSendTitle.isBlank()){
            throw new RuntimeException("?????? ????????? ??????????????????.");
        }
        if(messageSendContent.isBlank()){
            throw new RuntimeException("?????? ????????? ??????????????????.");
        }
    }
    public void messageReceivedSave(String messageReceiveTitle, String receiveMemberId, String messageSendContent, String loginMember, Optional<Member> SendMemberInfo) {
        MessageReceive messageReceive = new MessageReceive(messageReceiveTitle, messageSendContent, String.valueOf(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))), loginMember, receiveMemberId);
        messageReceive.addMessageReceive(SendMemberInfo.get());
        messageReceiveRepository.save(messageReceive);
    }

    public void messageSendSave(String messageSendTitle, String receiveMemberId, String messageSendContent, String loginMember, Optional<Member> SendMemberInfo) {
        MessageSend messageSend = new MessageSend(messageSendTitle, messageSendContent, String.valueOf(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))), loginMember, receiveMemberId);
        messageSend.addMessageSendMember(SendMemberInfo.get());
        messageSendRepository.save(messageSend);//Spring JPA save?????? ??????
    }

    public Pageable getPageableMemberList(Pageable pageable) {
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "memberId"));
        return pageable;
    }

    public Pageable getPageableMessageReceived(Pageable pageable) {
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "messageReceiveNo"));
        return pageable;
    }

    public Pageable getPageableMessageSend(Pageable pageable) {
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "messageSendNo"));
        return pageable;
    }
}

