package Healthduo.demo.service;

import Healthduo.demo.domain.Bbs;
import Healthduo.demo.domain.Comment;
import Healthduo.demo.domain.Member;
import Healthduo.demo.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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

        if (commentRepository.findcommentCnt().equals(Optional.empty())) {
            commentCnt = 0;
        } else {
            commentCnt = commentRepository.findcommentCnt().get() +1;
        }
        log.info("commentGroup={},commentCnt={}", commentGroup, commentCnt);
        Comment comment = new Comment(content, commentCnt, commentGroup, String.valueOf(LocalDate.now()));
        comment.addBbs(bbs);
        comment.addMember(member);
        commentRepository.contentSave(comment);
    }
}
