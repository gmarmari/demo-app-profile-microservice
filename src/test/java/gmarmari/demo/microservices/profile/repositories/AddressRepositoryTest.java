package gmarmari.demo.microservices.profile.repositories;

import gmarmari.demo.microservices.profile.entities.AddressDao;
import gmarmari.demo.microservices.profile.entities.AddressTypeDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static gmarmari.demo.microservices.profile.CommonDataFactory.aText;
import static gmarmari.demo.microservices.profile.ProfileDataFactory.aAddressDao;
import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@DataJpaTest
class AddressRepositoryTest {

    @Autowired
    private AddressRepository repository;

    @Autowired
    private TestEntityManager entityManager;


    @Test
    void save_findById() {
        // Given
        AddressDao dao = aAddressDao();
        Long id = entityManager.persistAndGetId(dao, Long.class);

        // When
        Optional<AddressDao> resultOptional = repository.findById(id);

        // Then
        assertThat(resultOptional).isPresent();
        AddressDao result = resultOptional.get();

        assertThat(result.getId()).isEqualTo(id);
        assertThat(result.getUsername()).isEqualTo(dao.getUsername());
        assertThat(result.getType()).isEqualTo(dao.getType());
        assertThat(result.getName()).isEqualTo(dao.getName());
        assertThat(result.getStreet()).isEqualTo(dao.getStreet());
        assertThat(result.getPostalCode()).isEqualTo(dao.getPostalCode());
        assertThat(result.getCity()).isEqualTo(dao.getCity());
        assertThat(result.getState()).isEqualTo(dao.getState());
        assertThat(result.getCountry()).isEqualTo(dao.getCountry());
        assertThat(result.getTel()).isEqualTo(dao.getTel());
    }

    @Test
    void save_findByUsername() {
        // Given
        String username = aText();

        AddressDao daoA = aAddressDao();
        daoA.setUsername(username);
        daoA.setType(AddressTypeDao.SHIPPING);
        entityManager.persist(daoA);

        AddressDao daoB = aAddressDao();
        daoB.setUsername(username);
        daoB.setType(AddressTypeDao.BILLING);
        entityManager.persist(daoB);

        // When
        List<AddressDao> list = repository.findByUsername(username, Sort.by("type").ascending());

        // Then
        assertThat(list).containsExactly(daoB, daoA);
    }


}