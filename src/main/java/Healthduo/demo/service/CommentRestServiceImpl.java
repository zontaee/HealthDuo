package Healthduo.demo.service;

import Healthduo.demo.domain.Bbs;
import Healthduo.demo.domain.Comment;
import Healthduo.demo.domain.Member;
import Healthduo.demo.dto.CommentDTO;
import Healthduo.demo.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CommentRestServiceImpl implements CommentRestService {
    private final CommentRepository commentRepository;

    @Override
    public void CommentSave(String content, Bbs bbs, Member member) {
        log.info("CommentSave(Service start)");
        Integer commentGroup;
        Integer commentCnt;
        if (commentRepository.findCommentGroup().equals(Optional.empty())) {
            commentGroup = 0;
        } else {
            commentGroup = commentRepository.findCommentGroup().get() + 1;
        }

        if (commentRepository.findCommentCnt().equals(Optional.empty())) {
            commentCnt = 0;
        } else {
            commentCnt = commentRepository.findCommentCnt().get() + 1;
        }
        log.info("commentGroup={},commentCnt={}", commentGroup, commentCnt);
        Comment comment = new Comment(content, commentCnt, commentGroup, String.valueOf(LocalDate.now()));
        comment.addBbs(bbs);
        comment.addMember(member);
        commentRepository.contentSave(comment);
    }

    @Override
    public List<CommentDTO> commentFind(Long bbsNo) {
        log.info("commentFind(Service start)");
        List<CommentDTO>  commentDTO = new ArrayList<>();
        List<Comment> comments = commentRepository.contentFind(bbsNo);
        for (Comment comment : comments) {
            CommentDTO recommentDTO = new CommentDTO(comment.getCommentId(),comment.getContent(),comment.getCommentCnt(),
                    comment.getCommentGroup(),comment.getDate(),comment.getCommentSequence(),comment.getLevel(),comment.getMember().getMemberId());
            commentDTO.add(recommentDTO);
        }
        return commentDTO;
    }

    @Override
    public void commentDelete(int commentGroup) {
        log.info("commentDelete(Service start)");
        commentRepository.deleteByCommentGroup(commentGroup);
    }

    @Override
    public void childCommentSave(String content, Bbs bbs, Member member, int commentGroup) {
        log.info("childCommentSave(Service start)");
        Integer commentSequence;
        Integer commentCnt;
        Integer level;
        Integer commentGroupnubmer = commentGroup;
        if (commentRepository.findCommentCnt().equals(Optional.empty())) {
            commentCnt = 0;
        } else {
            commentCnt = commentRepository.findCommentCnt().get() + 1;
        }
        if (commentRepository.findCommentSequence().equals(Optional.empty())) {
            commentSequence = 0;
        } else {
            commentSequence = commentRepository.findCommentSequence().get() + 1;
        }
        if (commentRepository.findLevel().equals(Optional.empty())) {
            level = 0;
        } else {
            level = commentRepository.findLevel().get() + 1;
        }
        log.info("commentGroup={},commentSequence={},commentSequence={},commentGroupnubmer={}"+
                commentSequence, commentCnt,level,commentGroupnubmer);
        Comment comment = new Comment(content, commentCnt, commentGroup, String.valueOf(LocalDate.now()),commentSequence,level);
        comment.addBbs(bbs);
        comment.addMember(member);
        commentRepository.contentSave(comment);

    }
}
