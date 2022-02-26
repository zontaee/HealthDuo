package Healthduo.demo.repository;

import Healthduo.demo.domain.Bbs;
import Healthduo.demo.domain.Comment;

import java.util.List;
import java.util.Optional;


public interface CommentRepositoryCustom  {
    Comment contentSave(Comment comment);
    List<Comment> contentFind(Long bbsNo);
    void deleteByCommentGroup(int commentGroup );
    Optional<Comment> commentChildInfo(String childinfo);
    Integer findCommentSequence(String childinfo);
    Integer findLevel(String childinfo);
    Integer findCommentSequence(Integer Groupnumber);
    void updateSeqyebce(int seq);
    void updateCheck(int seq);
}
