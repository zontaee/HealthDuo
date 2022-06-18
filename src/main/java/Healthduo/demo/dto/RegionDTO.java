package Healthduo.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegionDTO {

    private String region;
    private String City;
    private String FullCity;
    private String Street;

    public RegionDTO(String region, String city, String fullCity, String street) {
        this.region = region;
        this.City = city;
        this.FullCity = fullCity;
        this.Street = street;
    }
}
