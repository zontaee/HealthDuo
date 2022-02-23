package Healthduo.demo.repository;

import Healthduo.demo.domain.Bbs;
import Healthduo.demo.domain.Comment;

import java.util.List;


public interface CommentRepositoryCustom  {
    Comment contentSave(Comment comment);
    List<Comment> contentFind(Long bbsNo);
    void deleteByCommentGroup(int commentGroup );
}
