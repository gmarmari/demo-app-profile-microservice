package gmarmari.demo.microservices.profile.repositories;

import gmarmari.demo.microservices.profile.entities.PersonalDataDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static gmarmari.demo.microservices.profile.CommonDataFactory.aText;
import static gmarmari.demo.microservices.profile.ProfileDataFactory.aPersonalDataDao;
import static org.assertj.core.api.Assertions.assertThat;


@ActiveProfiles("test")
@DataJpaTest
class PersonalDataRepositoryTest {

    @Autowired
    private PersonalDataRepository repository;

    @Autowired
    private TestEntityManager entityManager;


    @Test
    void save_findById() {
        // Given
        PersonalDataDao dao = aPersonalDataDao();
        Long id = entityManager.persistAndGetId(dao, Long.class);

        // When
        Optional<PersonalDataDao> resultOptional = repository.findById(id);

        // Then
        assertThat(resultOptional).isPresent();
        PersonalDataDao result = resultOptional.get();

        assertThat(result.getId()).isEqualTo(id);
        assertThat(result.getUsername()).isEqualTo(dao.getUsername());
        assertThat(result.getSalutation()).isEqualTo(dao.getSalutation());
        assertThat(result.getFirstName()).isEqualTo(dao.getFirstName());
        assertThat(result.getLastName()).isEqualTo(dao.getLastName());
        assertThat(result.getEmail()).isEqualTo(dao.getEmail());
        assertThat(result.getPassword()).isEqualTo(dao.getPassword());
    }

    @Test
    void save_findByUsername() {
        // Given
        String username = aText();
        PersonalDataDao dao = aPersonalDataDao();
        dao.setUsername(username);
        entityManager.persist(dao);

        // When
        Optional<PersonalDataDao> resultOptional = repository.findByUsername(username);

        // Then
        assertThat(resultOptional).isPresent();
        PersonalDataDao result = resultOptional.get();

        assertThat(result.getUsername()).isEqualTo(dao.getUsername());
        assertThat(result.getSalutation()).isEqualTo(dao.getSalutation());
        assertThat(result.getFirstName()).isEqualTo(dao.getFirstName());
        assertThat(result.getLastName()).isEqualTo(dao.getLastName());
        assertThat(result.getEmail()).isEqualTo(dao.getEmail());
        assertThat(result.getPassword()).isEqualTo(dao.getPassword());
    }

}