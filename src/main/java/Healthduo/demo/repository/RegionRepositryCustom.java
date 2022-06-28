package Healthduo.demo.repository;

import Healthduo.demo.domain.Region;

import java.util.List;

public interface RegionRepositryCustom {
    List<String> findRegion();

    List<String> findCity(String region);

    List<String> findFullCity(String region);

    List<String> findFullCity2(String city);

    List<String> findStreet(String fullCity);
}
