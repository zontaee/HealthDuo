package Healthduo.demo.repository;

import Healthduo.demo.domain.QComment;
import Healthduo.demo.domain.QRegion;
import Healthduo.demo.domain.Region;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;


public class RegionRepositryCustomImpl implements RegionRepositryCustom {
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public RegionRepositryCustomImpl(EntityManager em, JPAQueryFactory queryFactory) {
        this.em = em;
        this.queryFactory = queryFactory;
    }

    QRegion region = new QRegion("region");

    @Override
    public List<String> findRegion() {
        List<String> findRegion = queryFactory.select(region.region).distinct()
                .from(region)
                .fetch();

        return findRegion;
    }

    @Override
    public List<String> findCity(String regions) {
        List<String> findCity = queryFactory.select(region.city).distinct()
                .from(region)
                .where(region.region.eq(regions))
                .fetch();
        return findCity;
    }

    @Override
    public List<String> findFullCity(String regions) {
        List<String> findFullCity = queryFactory.select(region.fullCity).distinct()
                .from(region)
                .where(region.region.eq(regions))
                .fetch();
        return findFullCity;
    }

    @Override
    public List<String> findFullCity2(String citys) {
        List<String> findFullCity = queryFactory.select(region.fullCity).distinct()
                .from(region)
                .where(region.city.eq(citys))
                .fetch();
        return findFullCity;
    }

    @Override
    public List<String> findStreet(String fullCitys) {
        List<String> street = queryFactory.select(region.Street).distinct()
                .from(region)
                .where(region.fullCity.eq(fullCitys))
                .fetch();
        return street;
    }
}
