package Healthduo.demo.service;

import Healthduo.demo.domain.Region;
import Healthduo.demo.repository.RegionRepository;
import Healthduo.demo.web.ServiceMethod;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class RegionServiceImpl implements RegionService {
    private final RegionRepository regionRepository;
    private final ServiceMethod serviceMethod;


    @Override
    public List<String> getRegionInfo() {
        log.info("getRegionInfo(controller start)");
            List<String> regions = regionRepository.findRegion();
        return regions;
    }

    @Override
    public List<String> cityInfo(String region) {
        log.info("cityInfo(controller start)");
        return serviceMethod.distinguishRegion(region);

    }


    @Override
    public List<String> fullCityInfo(String city) {
        log.info("findfullCity(controller start)");
        List<String> fullCity = regionRepository.findFullCity2(city);
      return fullCity;
    }

    @Override
    public List<String> streetInfo(String fullCity) {
        log.info("streetInfo(controller start)");
        List<String> street = regionRepository.findStreet(fullCity);
        return street;
    }
}
