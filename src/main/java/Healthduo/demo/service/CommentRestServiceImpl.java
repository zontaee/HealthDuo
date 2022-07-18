package Healthduo.demo.service;

import Healthduo.demo.domain.Bbs;
import Healthduo.demo.domain.Comment;
import Healthduo.demo.domain.Member;
import Healthduo.demo.dto.CommentDTO;
import Healthduo.demo.repository.CommentRepository;
import Healthduo.demo.repository.MemberRepository;
import Healthduo.demo.web.ServiceMethod;
import Healthduo.demo.web.TransferDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CommentRestServiceImpl implements CommentRestService {
    private final CommentRepository commentRepository;
    private final ServiceMethod serviceMethod;
    private final TransferDTO transferDTO;
    private final MemberRepository memberRepository;

    /**
     * 일반 댓글 저장
     * @param content
     * @param bbs
     * @param loginMember
     */
    @Override
    public void CommentSave(String content, Bbs bbs, String loginMember) {
        log.info("CommentSave(Service start)");
        Integer commentGroup;
        Integer commentCnt;
        commentGroup = serviceMethod.incrementGroup();
        commentCnt = serviceMethod.incrementCnt();
        Member member = memberRepository.findById(loginMember).orElse(null);
        serviceMethod.saveComment(content, bbs, member, commentGroup, commentCnt);
    }


    @Override
    public List<CommentDTO> commentFind(Long bbsNo) {
        log.info("commentFind(Service start)");
        List<CommentDTO> commentDTO = new ArrayList<>();
        List<Comment> comments = commentRepository.contentFind(bbsNo);
        transferDTO.transferCommentDTO(commentDTO, comments);
        return commentDTO;
    }

    @Override
    public void commentDelete(int commentGroup, int commentSequence) {
        log.info("commentDelete(Service start)");
        commentRepository.deleteComment(commentGroup, commentSequence);


    }

    /**
     * 대댓글 저장
     * checkInfo -> 부모댓글 구분
     * @param content
     * @param bbs
     * @param loginMember
     * @param childinfo
     * @param seq
     */
    @Override
    public void childCommentSave(String content, Bbs bbs, String loginMember, String childinfo, int seq) {
        String childInfo = childinfo;
        String[] sliceChildInfo = childInfo.split("L");
        Integer commentGroupNubmer = Integer.parseInt(sliceChildInfo[1]);
        Integer commentSequence;
        Integer commentCnt;
        Integer level;
        Integer check = 0;
        Member member = memberRepository.findById(loginMember).orElse(null);
        Integer commentSequenceFinded = commentRepository.findCommentMaxSequence(commentGroupNubmer);
        if (Integer.parseInt(sliceChildInfo[2]) == 0) { //처음 댓글이 달렸을때
            commentSequence = commentSequenceFinded + 1;
            level = 1;
            commentCnt = commentRepository.findCommentCnt().get() + 1;
        } else { // 대댓글에 대댓글이 달렸을때
            commentSequence = serviceMethod.sortLogic(seq, sliceChildInfo, commentGroupNubmer); //
            commentRepository.updateCheck(seq);// 대댓글에 댓글이 달렸을때 해당 대댓글에 CheckInfo =1 값을 주어 부모 객체로 만들어준다
            commentCnt = serviceMethod.incrementCnt();
            level = serviceMethod.incrementLevel(sliceChildInfo);
        }
        Comment comment = new Comment(content, commentCnt, commentGroupNubmer, String.valueOf(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))), commentSequence, level, childInfo, check);
        comment.addBbs(bbs);
        comment.addMember(member);
        commentRepository.contentSave(comment);
    }

}

