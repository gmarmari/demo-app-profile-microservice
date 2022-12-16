package gmarmari.demo.microservices.profile.repositories;

import gmarmari.demo.microservices.profile.entities.PaymentMethodDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static gmarmari.demo.microservices.profile.CommonDataFactory.aText;
import static gmarmari.demo.microservices.profile.ProfileDataFactory.aPaymentMethodDao;
import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@DataJpaTest
class PaymentMethodRepositoryTest {

    @Autowired
    private PaymentMethodRepository repository;

    @Autowired
    private TestEntityManager entityManager;


    @Test
    void save_findById() {
        // Given
        PaymentMethodDao dao = aPaymentMethodDao();
        Long id = entityManager.persistAndGetId(dao, Long.class);

        // When
        Optional<PaymentMethodDao> resultOptional = repository.findById(id);

        // Then
        assertThat(resultOptional).isPresent();
        PaymentMethodDao result = resultOptional.get();

        assertThat(result.getId()).isEqualTo(id);
        assertThat(result.getUsername()).isEqualTo(dao.getUsername());
        assertThat(result.getType()).isEqualTo(dao.getType());
        assertThat(result.getText1()).isEqualTo(dao.getText1());
        assertThat(result.getText2()).isEqualTo(dao.getText2());
    }

    @Test
    void save_findByUsername() {
        // Given
        String username = aText();
        PaymentMethodDao dao = aPaymentMethodDao();
        dao.setUsername(username);
        entityManager.persist(dao);

        // When
        List<PaymentMethodDao> list = repository.findByUsername(username);

        // Then
        assertThat(list).hasSize(1);

        assertThat(list.get(0).getUsername()).isEqualTo(dao.getUsername());
        assertThat(list.get(0).getType()).isEqualTo(dao.getType());
        assertThat(list.get(0).getText1()).isEqualTo(dao.getText1());
        assertThat(list.get(0).getText2()).isEqualTo(dao.getText2());
    }

}