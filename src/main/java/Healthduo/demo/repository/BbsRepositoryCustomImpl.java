package Healthduo.demo.repository;

import Healthduo.demo.domain.Bbs;
import Healthduo.demo.domain.Member;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
@RequiredArgsConstructor
public class BbsRepositoryCustomImpl implements BbsRepositoryCustom{
    private  final EntityManager em;
    @Override
    public Bbs BbsSave(Bbs bbs) {
        em.persist(bbs);
        return bbs;
    }
}
