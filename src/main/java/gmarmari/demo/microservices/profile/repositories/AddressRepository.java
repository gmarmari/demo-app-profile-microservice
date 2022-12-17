package gmarmari.demo.microservices.profile.repositories;

import gmarmari.demo.microservices.profile.entities.AddressDao;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<AddressDao, Long> {

    List<AddressDao> findByUsername(String username, Sort sort);
}
