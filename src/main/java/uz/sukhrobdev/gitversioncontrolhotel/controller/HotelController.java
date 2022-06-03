package uz.sukhrobdev.gitversioncontrolhotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.sukhrobdev.gitversioncontrolhotel.entity.Address;
import uz.sukhrobdev.gitversioncontrolhotel.entity.Hotel;
import uz.sukhrobdev.gitversioncontrolhotel.entity.Room;
import uz.sukhrobdev.gitversioncontrolhotel.payload.HotelDto;
import uz.sukhrobdev.gitversioncontrolhotel.repository.AddressRepository;
import uz.sukhrobdev.gitversioncontrolhotel.repository.HotelRepository;
import uz.sukhrobdev.gitversioncontrolhotel.repository.RoomRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/hotel")
public class HotelController {
    final AddressRepository addressRepository;
    final HotelRepository hotelRepository;
    final RoomRepository roomRepository;

    public HotelController(AddressRepository addressRepository, HotelRepository hotelRepository,RoomRepository roomRepository) {
        this.addressRepository = addressRepository;
        this.hotelRepository = hotelRepository;
        this.roomRepository = roomRepository;
    }

    @GetMapping
    public List<Hotel> getHotels() {
        return hotelRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public Hotel getHotel(@PathVariable Integer id) {
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        return optionalHotel.orElseGet(Hotel::new);
    }

    @PostMapping
    public String addHotel(@RequestBody HotelDto hotelDto) {
        Address address = new Address(hotelDto.getCountry(), hotelDto.getRegion(), hotelDto.getDistrict(), hotelDto.getStreet(), hotelDto.getHome());
        Address savedAddress = addressRepository.save(address);
        // List<Room> listRoom = new ArrayList<>();
//        for (Integer integer : hotelDto.getRoomList()) {
//            Optional<Room> optionalRoom = roomRepository.findById(integer);
//            if (optionalRoom.isPresent()) {
//                listRoom.add(optionalRoom.get());
//            } else {
//
//            }
//        } for @OneToMany
        // Hotel hotel = new Hotel(hotelDto.getName(), savedAddress,listRoom);
        Hotel hotel = new Hotel(hotelDto.getName(), savedAddress);
        hotelRepository.save(hotel);
        return "The new hotel has been successfully added!";
    }

    @PutMapping(value = "/{id}")
    public String editHotel(@PathVariable Integer id, @RequestBody HotelDto hotelDto) {
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        if (optionalHotel.isPresent()) {
            Hotel hotel = optionalHotel.get();
            Address address = hotel.getAddress();
            address.setCountry(hotelDto.getCountry());
            address.setRegion(hotelDto.getRegion());
            address.setDistrict(hotelDto.getDistrict());
            address.setStreet(hotelDto.getStreet());
            address.setHome(hotelDto.getHome());
            addressRepository.save(address);
            hotel.setName(hotelDto.getName());
            hotelRepository.save(hotel);
            return "The hotel has been successfully edited!";
        } else {
            return "The hotel has not been found to edit!";
        }
    }

    @DeleteMapping(value = "/{id}")
    public String deleteHotel(@PathVariable Integer id) {
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        if (optionalHotel.isPresent()) {
            Hotel hotel = optionalHotel.get();
            Address address = hotel.getAddress();
            addressRepository.delete(address);
            hotelRepository.delete(hotel);
            return "The hotel has been successfully delete";
        } else {
            return "The hotel has not been found to delete";
        }
    }
}
