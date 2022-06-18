package Healthduo.demo.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter @Setter @ToString
public class Region {
    private String region;
    private String city;
    private String fullCity;
    @Id
    private String Street;
    public Region() {
    }

    public Region(String region, String fullCity, String street) {
        this.region = region;
        this.fullCity = fullCity;
        this.Street = street;
    }

    public Region(String region, String city, String fullCity, String street) {
        this.region = region;
        this.city = city;
        this.fullCity = fullCity;
        this.Street = street;
    }
}
