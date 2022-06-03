package uz.sukhrobdev.gitversioncontrolhotel.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String country;
    @Column(nullable = false)
    private String region;
    @Column(nullable = false)
    private String district;
    @Column(nullable = false)
    private String street;
    @Column(nullable = false)
    private String home;

    public Address(String country, String region, String district, String street, String home) {
        this.country = country;
        this.region = region;
        this.district = district;
        this.street = street;
        this.home = home;
    }
}
