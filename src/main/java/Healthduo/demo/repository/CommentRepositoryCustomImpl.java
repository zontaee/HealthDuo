package Healthduo.demo.repository;

import Healthduo.demo.domain.Bbs;
import Healthduo.demo.domain.Comment;
import Healthduo.demo.domain.QComment;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import java.util.List;


public class CommentRepositoryCustomImpl implements CommentRepositoryCustom{
    private  final EntityManager em;
    private  final JPAQueryFactory queryFactory;

    public CommentRepositoryCustomImpl(EntityManager em, JPAQueryFactory queryFactory) {
        this.em = em;
        this.queryFactory = queryFactory;
    }

    QComment comment = new QComment("comment");
    @Override
    public Comment contentSave(Comment comment) {
        em.persist(comment);
        return comment;
    }

    @Override
    public List<Comment> contentFind(Long bbsNo) {
        List<Comment> contentFind = queryFactory
                .selectFrom(comment)
                .where(comment.bbs.bbsNo.eq(bbsNo))
                .orderBy(comment.commentGroup.desc(),comment.commentSequence.asc())
                .fetch();
        return contentFind;
    }

    @Override
    public void deleteByCommentGroup(int commentGroup) {
        long execute = queryFactory
                .delete(comment)
                .where(comment.commentGroup.eq(commentGroup))
                .execute();
    }

}
