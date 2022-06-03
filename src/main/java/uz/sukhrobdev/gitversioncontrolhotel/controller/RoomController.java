package uz.sukhrobdev.gitversioncontrolhotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.sukhrobdev.gitversioncontrolhotel.entity.Hotel;
import uz.sukhrobdev.gitversioncontrolhotel.entity.Room;
import uz.sukhrobdev.gitversioncontrolhotel.payload.RoomDto;
import uz.sukhrobdev.gitversioncontrolhotel.repository.HotelRepository;
import uz.sukhrobdev.gitversioncontrolhotel.repository.RoomRepository;

import java.util.Optional;

@RestController
@RequestMapping(value = "/room")
public class RoomController {
    final HotelRepository hotelRepository;
    final RoomRepository roomRepository;

    public RoomController(HotelRepository hotelRepository, RoomRepository roomRepository) {
        this.hotelRepository = hotelRepository;
        this.roomRepository = roomRepository;
    }

    @GetMapping(value = "/forMinistry")
    public Page<Room> getRoomsForMinistry(@RequestParam int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return roomRepository.findAll(pageable);
    }

    @GetMapping(value = "/forHotel/{hotelId}")
    public Page<Room> getRoomsForHotel(@PathVariable Integer hotelId, @RequestParam int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return roomRepository.findAllByHotelId(hotelId, pageable);
    }

    @PostMapping
    public String addRoom(@RequestBody RoomDto roomDto) {
        Optional<Hotel> optionalHotel = hotelRepository.findById(roomDto.getHotelId());
        if (optionalHotel.isPresent()) {
            Hotel hotel = optionalHotel.get();
            Room room = new Room(roomDto.getNumber(), roomDto.getFloor(), roomDto.getSize(), hotel);
            roomRepository.save(room);
            return "The room has been successfully saved!";
        } else {
            return "The hotel has not been found to add a new room to it!";
        }
    }

    @PutMapping(value = "/{id}")
    public String editRoom(@PathVariable Integer id, @RequestBody RoomDto roomDto) {
        Optional<Hotel> optionalHotel = hotelRepository.findById(roomDto.getHotelId());
        Optional<Room> optionalRoom = roomRepository.findById(id);
        if (optionalRoom.isPresent() && optionalHotel.isPresent()) {
            Hotel hotel = optionalHotel.get();
            Room room = optionalRoom.get();
            room.setNumber(roomDto.getNumber());
            room.setFloor(roomDto.getFloor());
            room.setSize(roomDto.getSize());
            room.setHotel(hotel);
            roomRepository.save(room);
            return "The room has been successfully edited!";
        } else {
            return "The room or hotel which you want to add a new room has not been found to edit!";
        }
    }

    @DeleteMapping(value = "/{id}")
    public String deleteRoom(@PathVariable Integer id) {
        Optional<Room> optionalRoom = roomRepository.findById(id);
        if (optionalRoom.isPresent()) {
            roomRepository.delete(optionalRoom.get());
            return "The room has been successfully deleted!";
        } else {
            return "The room has not been found to delete!";
        }
    }
}
