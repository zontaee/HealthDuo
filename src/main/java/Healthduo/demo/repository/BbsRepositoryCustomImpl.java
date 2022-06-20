package Healthduo.demo.repository;

import Healthduo.demo.domain.*;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.util.List;


public class BbsRepositoryCustomImpl implements BbsRepositoryCustom{
    private  final EntityManager em;
    private  final JPAQueryFactory queryFactory;

    public BbsRepositoryCustomImpl(EntityManager em, JPAQueryFactory queryFactory) {
        this.em = em;
        this.queryFactory = queryFactory;
    }
    QBbs bbs = new QBbs("bbs");

    @Override
    public Bbs BbsSave(Bbs bbs) {
        em.persist(bbs);
        return bbs;
    }

    @Override
    public Page<Bbs> findBySearchContent(String contents, Pageable pageable) {
        QueryResults<Bbs> results = queryFactory
                .select(bbs)
                .from(bbs)
                .where(bbs.bbsContent.like("%"+contents+"%"))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<Bbs> content = results.getResults();
        long total = results.getTotal();

        return new PageImpl<>(content,pageable,total);

    }

    @Override
    public Page<Bbs> findBySearchTitle(String title, Pageable pageable) {
        QueryResults<Bbs> results = queryFactory
                .select(bbs)
                .from(bbs)
                .where(bbs.bbsTitle.like("%"+title+"%"))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<Bbs> content = results.getResults();
        long total = results.getTotal();

        return new PageImpl<>(content,pageable,total);
    }

    @Override
    public Page<Bbs> findBySearchUserID(String UserID, Pageable pageable) {
        QueryResults<Bbs> results = queryFactory
                .select(bbs)
                .from(bbs)
                .where(bbs.member.memberId.like("%"+UserID+"%"))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<Bbs> content = results.getResults();
        long total = results.getTotal();

        return new PageImpl<>(content,pageable,total);
    }

    @Override
    public List<Bbs> findNoticeBbsList() {
        QueryResults<Bbs> results = queryFactory
                .select(bbs)
                .from(bbs)
                .where(bbs.checkNS.contains("n"))
                .fetchResults();

        return results.getResults();
    }

    @Override
    public Page<Bbs> findAllAddress(Pageable pageable, String address) {
        QueryResults<Bbs> results = queryFactory
                .select(bbs)
                .from(bbs)
                .where(bbs.address.eq(address))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
        List<Bbs> bbsList = results.getResults();
        long total = results.getTotal();
        return new PageImpl<>(bbsList,pageable,total);
    }
}
