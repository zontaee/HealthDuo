package Healthduo.demo.controller;

import Healthduo.demo.domain.Bbs;
import Healthduo.demo.domain.Member;
import Healthduo.demo.service.RegionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class RegionRestController {
    private final RegionService regionService;

    /**
     * region값을 통해 City name 찾기(경기도(region) -> 성남시(city))
     * @param region
     * @return
     */
    @PostMapping("findCity")
    public List<String> findCity(@RequestParam("region") String region) {
        log.info("findCity(controller start)");
        List<String> cityInfo = regionService.cityInfo(region);
        return cityInfo;
    }

    /**
     * city 통해 fullCity name 찾기(성남시(city) -> 분당구(fullCity))
     * @param city
     * @return
     */
    @PostMapping("findfullCity")
    public List<String> findfullCity(@RequestParam("city") String city) {
        log.info("findfullCity(controller start)");
        List<String> fullCityInfo = regionService.fullCityInfo(city);
        return fullCityInfo;
    }

    /**
     * fullCity 통해 Street name 찾기(분당구(fullCity) -> 분당구(Street))
     * @param fullCity
     * @return
     */

    @PostMapping("findStreet")
    public List<String> findStreet(@RequestParam("fullCity") String fullCity) {
        log.info("findStreet(controller start)");
        List<String> streetInfo = regionService.streetInfo(fullCity);
        return streetInfo;
    }


    @PostMapping("bbsRegion")
    public List<String> bbsregion() {
        log.info("bbsregion(controller start)");
        List<String> bbsRegion = regionService.getRegionInfo();
        return bbsRegion;
    }

    @PostMapping("bbsCity")
    public List<String> bbscity(@RequestParam("regionInfo") String regionInfo) {
        log.info("bbscity(controller start)");
        List<String> bbscity = regionService.cityInfo(regionInfo);
        return bbscity;
    }
}
