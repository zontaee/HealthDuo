package Healthduo.demo.repository;

import Healthduo.demo.domain.MessageReceive;
import Healthduo.demo.domain.QMessageReceive;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.util.List;

public class MessageReceiveRepositoryCustomImpl implements MessageReceiveRepositoryCustom {
    private  final EntityManager em;
    private  final JPAQueryFactory queryFactory;

    public MessageReceiveRepositoryCustomImpl(EntityManager em, JPAQueryFactory queryFactory) {
        this.em = em;
        this.queryFactory = queryFactory;
    }
    QMessageReceive messageReceive = new QMessageReceive("messageReceive");

    @Override
    public Page<MessageReceive> findMessageReceive(String loginMember, Pageable pageable) {
        QueryResults<MessageReceive> results = queryFactory
                .select(messageReceive)
                .from(messageReceive)
                .where(messageReceive.receiveMemberId.eq(loginMember))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<MessageReceive> content = results.getResults();
        long total = results.getTotal();

        return new PageImpl<>(content,pageable,total);

    }
}
