package Healthduo.demo.service;

import Healthduo.demo.domain.Region;

import java.util.List;

public interface RegionService {


    List<String> getRegionInfo();

    List<String> cityInfo(String region);

    List<String> fullCityInfo(String city);

    List<String> streetInfo(String fullCity);
}
