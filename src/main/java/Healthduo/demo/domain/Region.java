package Healthduo.demo.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter@Setter@ToString
public class Region {
    private String region;
    private String City;
    private String FullCity;
    @Id
    private String Street;
    public Region() {
    }

    public Region(String region, String city, String street) {
        this.region = region;
        City = city;
        Street = street;
    }

    public Region(String region, String city, String fullCity, String street) {
        this.region = region;
        City = city;
        FullCity = fullCity;
        Street = street;
    }
}
