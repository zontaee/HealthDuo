package Healthduo.demo.repository;

import Healthduo.demo.domain.MessageReceive;
import Healthduo.demo.domain.MessageSend;
import Healthduo.demo.domain.QMessageReceive;
import Healthduo.demo.domain.QMessageSend;
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
    QMessageSend messageSend = new QMessageSend("messageSend");

    @Override
    public Page<MessageReceive> findMessageReceiveList(String loginMember, Pageable pageable) {
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

    @Override
    public Page<MessageSend> findMessageSendList(String loginMember, Pageable pageable) {
        QueryResults<MessageSend> results = queryFactory
                .select(messageSend)
                .from(messageSend)
                .where(messageSend.sendMemberId.eq(loginMember))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<MessageSend> content = results.getResults();
        long total = results.getTotal();

        return new PageImpl<>(content,pageable,total);
    }
}
