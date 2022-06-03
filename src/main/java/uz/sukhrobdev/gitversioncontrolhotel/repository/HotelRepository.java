package uz.sukhrobdev.gitversioncontrolhotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.sukhrobdev.gitversioncontrolhotel.entity.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel,Integer> {
}
