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
    public void childCommentSave(String content, Bbs bbs, Member member, String childinfo, int seq) {
        log.info("childCommentSave(Service start)");
        String childInfo = childinfo;
        String[] sliceChildInfo = childInfo.split("L");
        log.info("childinfo={},sliceChildInfo[1]={},sliceChildInfo[2]={}",childInfo,sliceChildInfo[1],sliceChildInfo[2]);
        Integer commentGroupnubmer = Integer.parseInt(sliceChildInfo[1]);
        Integer commentSequence ;
        Integer commentCnt;
        Integer level ;
        Integer check = 0;
        Integer commentSequencefinded = commentRepository.findCommentSequence(commentGroupnubmer);
        log.info("commentSequencefinded = " + commentSequencefinded);


        Optional<Comment> findedChildInfo = commentRepository.commentChildInfo(childInfo);
        if(Integer.parseInt(sliceChildInfo[2])==0){
            log.info("1");
            commentSequence = commentSequencefinded +1;
            level =1;
            commentCnt = commentRepository.findCommentCnt().get() + 1;
        }else {


            if (Integer.parseInt(sliceChildInfo[3])==0) {
                log.info("2");
                commentSequence = seq +1;
                commentRepository.updateSeqyebce(seq);
                commentRepository.updateCheck(seq);
                if (commentRepository.findCommentCnt().equals(Optional.empty())) {
                    commentCnt = 0;

                } else {
                    commentCnt = commentRepository.findCommentCnt().get() + 1;
                }
                if (commentRepository.findLevel().equals(Optional.empty())) {
                    level = 0;
                } else {
                    level = Integer.parseInt(sliceChildInfo[2]) + 1;
                }
            } else {
                log.info("3");
                commentSequence = seq +1;
                commentRepository.updateSeqyebce(seq);

                if (commentRepository.findCommentCnt().equals(Optional.empty())) {
                    commentCnt = 0;
                } else {
                    commentCnt = commentRepository.findCommentCnt().get() + 1;
                }
                if (commentRepository.findLevel().equals(Optional.empty())) {
                    level = 0;
                } else {
                    level = Integer.parseInt(sliceChildInfo[2]);
                }
            }
        }



        log.info("commentGroupnubmer={},commentSequence={},level={},commentGroupnubmer={}"+
                commentGroupnubmer, commentSequence,level,commentGroupnubmer);
        Comment comment = new Comment(content, commentCnt, commentGroupnubmer, String.valueOf(LocalDate.now()),commentSequence,level,childInfo ,check);
        comment.addBbs(bbs);
        comment.addMember(member);
        commentRepository.contentSave(comment);

    }
}
