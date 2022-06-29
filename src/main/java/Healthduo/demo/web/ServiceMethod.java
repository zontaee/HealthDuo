package Healthduo.demo.web;

import Healthduo.demo.domain.Bbs;
import Healthduo.demo.domain.Comment;
import Healthduo.demo.domain.Member;
import Healthduo.demo.repository.BbsRepository;
import Healthduo.demo.repository.CommentRepository;
import Healthduo.demo.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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

    public Pageable getPageable(Pageable pageable) {
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "bbsNo"));
        return pageable;
    }

    public Page<Bbs> resultBySearch(Pageable pageable, String bbsListSearch, String searchText, Page<Bbs> searchedBbsList) {
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

    public void bbsLnitialization(Bbs bbs, String street, Member member) {
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
    //loginCheck = 1 등록된 아이디가 없습니다.
    //loginCheck = 2 아이디 비밀번호 둘다 일치
    //loginCheck = 3 등록된 비밀번호가 틀렸습니다.


    public int CheckIdNumber(Member findId) {
        int checkIdNumber;
        if (findId == null) {
            checkIdNumber = 0; //중복회원x
        } else {
            checkIdNumber = 1; //중복회원o
        }
        return checkIdNumber;
    }

    /**
     * ex 경기도면 성남시 서울특별시면 강남구 시랑 구랑 다르기때문에 구별해주는 메소드가 필요하다.
     * @param region
     * @return
     */
    public List<String> distinguishRegion(String region) {
        if (region.equals("서울특별시")) {
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
        Integer sameLevelAndGroupMaxSeq = commentRepository.findSameLevelAndGroupMaxSeq(commentGroupNubmer, Integer.parseInt(sliceChildInfo[2])+1);
        if(sameLevelAndGroupMaxSeq == null){
            commentSequence = seq + 1;
            commentRepository.updateAllSequence(seq);
        }else {
            if(seq <= sameLevelAndGroupMaxSeq){
                commentSequence = sameLevelAndGroupMaxSeq +1;
                commentRepository.updateAllSequence(sameLevelAndGroupMaxSeq);
            }else {
                commentSequence = seq + 1;
                commentRepository.updateAllSequence(seq);
            }
        }
        return commentSequence;
    }


}

