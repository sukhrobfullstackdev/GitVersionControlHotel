package uz.sukhrobdev.gitversioncontrolhotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.sukhrobdev.gitversioncontrolhotel.entity.Address;
@Repository
public interface AddressRepository extends JpaRepository<Address,Integer> {
}
