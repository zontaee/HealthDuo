package Healthduo.demo.repository;

import Healthduo.demo.domain.Bbs;
import Healthduo.demo.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


public interface CommentRepository  extends JpaRepository<Comment,Long>,CommentRepositoryCustom{
    @Query("select max(c.commentGroup) from Comment c")
    Optional<Integer> findCommentGroup();
    @Query("select max(c.commentCnt) from Comment c")
    Optional<Integer> findcommentCnt();
}
