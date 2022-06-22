package Healthduo.demo.repository;

import Healthduo.demo.domain.Comment;
import Healthduo.demo.domain.QComment;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;


public class CommentRepositoryCustomImpl implements CommentRepositoryCustom {
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

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
                .orderBy(comment.commentGroup.asc(), comment.commentSequence.asc())
                .fetch();
        return contentFind;
    }

    @Override
    public void deleteByCommentGroup(int commentGroup) {
                 queryFactory
                .delete(comment)
                .where(comment.commentGroup.eq(commentGroup))
                .execute();
    }

    @Override
    public Optional<Comment> commentChildInfo(String childinfo) {
        Optional<Comment> comemntChildInfo = queryFactory
                .selectFrom(comment)
                .where(comment.childInfo.eq(childinfo))
                .stream().findAny();
        return comemntChildInfo;
    }

    @Override
    public Integer findCommentSequence(String childinfo) {

        Integer findCommentsequence = queryFactory
                .select(comment.commentSequence.max())
                .from(comment)
                .where(comment.childInfo.eq(childinfo))
                .fetchOne();

        return findCommentsequence;
    }

    @Override
    public Integer findLevel(String childInfo) {
        Integer findLevel = queryFactory
                .select(comment.level.max())
                .from(comment)
                .where(comment.childInfo.eq(childInfo))
                .fetchOne();
        return findLevel;
    }



    @Override
    public Integer findSameLevelAndGroupMaxSeq(Integer group, Integer level) {
        Integer findLevel = queryFactory
                .select(comment.commentSequence.max())
                .from(comment)
                .where(comment.commentGroup.eq(group).and(comment.level.eq(level)))
                .fetchOne();
        return findLevel;
    }


    @Override
    public Integer findCommentSequence(Integer GroupNumber) {
        Integer findCommentSequence = queryFactory
                .select(comment.commentSequence.max())
                .from(comment)
                .where(comment.commentGroup.eq(GroupNumber))
                .fetchOne();
        return findCommentSequence;
    }

    @Override
    public void updateAllSequence(int seq) {
        queryFactory
                .update(comment)
                .set(comment.commentSequence, comment.commentSequence.add(1))
                .where(comment.commentSequence.gt(seq))
                .execute();

    }

    @Override
    public void updateCheck(int seq) {
        queryFactory
                .update(comment)
                .set(comment.checkInfo, 1)
                .where(comment.commentSequence.eq(seq))
                .execute();

    }

    @Override
    public void deleteComment(int commentGroup, int commentSequence) {
        queryFactory
                .delete(comment)
                .where(comment.commentGroup.eq(commentGroup), comment.commentSequence.eq(commentSequence))
                .execute();

    }


}
