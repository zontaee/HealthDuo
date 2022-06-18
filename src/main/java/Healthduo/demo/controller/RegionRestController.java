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


    @PostMapping("findCity")
    public List<String> findCity(@RequestParam("region") String region) {
        log.info("findCity(controller start)");
        List<String> cityInfo = regionService.cityInfo(region);
        return cityInfo;
    }

    @PostMapping("findfullCity")
    public List<String> findfullCity(@RequestParam("city") String city) {
        log.info("findfullCity(controller start)");
        List<String> fullCityInfo = regionService.fullCityInfo(city);
        return fullCityInfo;
    }

    @PostMapping("findStreet")
    public List<String> findStreet(@RequestParam("fullCity") String fullCity) {
        log.info("findStreet(controller start)");
        List<String> streetInfo = regionService.streetInfo(fullCity);
        return streetInfo;
    }
}
