package gmarmari.demo.microservices.profile.repositories;

import gmarmari.demo.microservices.profile.entities.PersonalDataDao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonalDataRepository extends JpaRepository<PersonalDataDao, Long> {

    Optional<PersonalDataDao> findByUsername(String username);

}
