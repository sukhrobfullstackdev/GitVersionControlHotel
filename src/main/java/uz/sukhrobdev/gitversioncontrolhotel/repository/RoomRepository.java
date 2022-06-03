package uz.sukhrobdev.gitversioncontrolhotel.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.sukhrobdev.gitversioncontrolhotel.entity.Room;


@Repository
public interface RoomRepository extends JpaRepository<Room,Integer> {
    Page<Room> findAllByHotelId(Integer hotel_id, Pageable pageable);
}
