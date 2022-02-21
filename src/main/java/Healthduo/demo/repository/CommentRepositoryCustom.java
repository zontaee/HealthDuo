package Healthduo.demo.repository;

import Healthduo.demo.domain.Bbs;
import Healthduo.demo.domain.Comment;


public interface CommentRepositoryCustom  {
    Comment contentSave(Comment comment);
}
