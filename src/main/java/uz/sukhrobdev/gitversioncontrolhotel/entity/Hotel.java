package uz.sukhrobdev.gitversioncontrolhotel.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @OneToOne
    private Address address;
//    @OneToMany
//    private List<Room> rooms;

    public Hotel(String name, Address address) {
        this.name = name;
        this.address = address;
    }

//    public Hotel(String name, Address address, List<Room> rooms) {
//        this.name = name;
//        this.address = address;
//        this.rooms = rooms;
//    }
}
