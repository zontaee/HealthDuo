package Healthduo.demo.repository;


import Healthduo.demo.domain.QRegion;

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

    /**
     * 지역 정보 다음 구정보가 필요할 시 쿼리(서울특별시 강남구)
     * @param regions
     * @return
     */
    @Override
    public List<String> findFullCity(String regions) {
        List<String> findFullCity = queryFactory.select(region.fullCity).distinct()
                .from(region)
                .where(region.region.eq(regions))
                .fetch();
        return findFullCity;
    }

    /**
     * 시정보 다음 구정보가 필요할 시 쿼리(경기도 성남시 분당구)
     * @param citys
     * @return
     */
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
        List<String> findStreet = queryFactory.select(region.Street).distinct()
                .from(region)
                .where(region.fullCity.eq(fullCitys))
                .fetch();
        return findStreet;
    }
}
