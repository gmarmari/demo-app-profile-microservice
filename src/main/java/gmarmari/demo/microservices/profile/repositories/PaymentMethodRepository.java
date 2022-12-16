package gmarmari.demo.microservices.profile.repositories;

import gmarmari.demo.microservices.profile.entities.PaymentMethodDao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethodDao, Long> {

    List<PaymentMethodDao> findByUsername(String username);

}
