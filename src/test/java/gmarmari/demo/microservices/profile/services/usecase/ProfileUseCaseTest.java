package gmarmari.demo.microservices.profile.services.usecase;

import gmarmari.demo.microservices.profile.entities.AddressDao;
import gmarmari.demo.microservices.profile.entities.PaymentMethodDao;
import gmarmari.demo.microservices.profile.entities.PersonalDataDao;
import gmarmari.demo.microservices.profile.repositories.AddressRepository;
import gmarmari.demo.microservices.profile.repositories.PaymentMethodRepository;
import gmarmari.demo.microservices.profile.repositories.PersonalDataRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

import static gmarmari.demo.microservices.profile.CommonDataFactory.aLong;
import static gmarmari.demo.microservices.profile.CommonDataFactory.aText;
import static gmarmari.demo.microservices.profile.ProfileDataFactory.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProfileUseCaseTest {

    @Mock
    private PersonalDataRepository personalDataRepository;

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private PaymentMethodRepository paymentMethodRepository;

    @InjectMocks
    private ProfileUseCase useCase;

    @Test
    void getPersonalData() {
        // Given
        String username = aText();
        PersonalDataDao dao = aPersonalDataDao(true);

        when(personalDataRepository.findByUsername(username)).thenReturn(Optional.of(dao));

        // WHen
        Optional<PersonalDataDao> optionalResult = useCase.getPersonalData(username);

        // Then
        assertThat(optionalResult).isPresent();
        assertThat(optionalResult.get()).isEqualTo(dao);

        verifyNoMoreInteractions(personalDataRepository);
        verifyNoInteractions(addressRepository);
        verifyNoInteractions(paymentMethodRepository);
    }

    @Test
    void getAddresses() {
        // Given
        String username = aText();
        List<AddressDao> list = List.of(aAddressDao(true), aAddressDao(true));

        when(addressRepository.findByUsername(username,  Sort.by("type").ascending())).thenReturn(list);

        // WHen
        List<AddressDao> result = useCase.getAddresses(username);

        // Then
        assertThat(result).containsExactlyElementsOf(list);

        verifyNoInteractions(personalDataRepository);
        verifyNoMoreInteractions(addressRepository);
        verifyNoInteractions(paymentMethodRepository);
    }

    @Test
    void getPaymentMethods() {
        // Given
        String username = aText();
        List<PaymentMethodDao> list = List.of(aPaymentMethodDao(true), aPaymentMethodDao(true));

        when(paymentMethodRepository.findByUsername(username,  Sort.by("type").ascending())).thenReturn(list);

        // When
        List<PaymentMethodDao> result = useCase.getPaymentMethods(username);

        // Then
        assertThat(result).containsExactlyElementsOf(list);

        verifyNoInteractions(personalDataRepository);
        verifyNoInteractions(addressRepository);
        verifyNoMoreInteractions(paymentMethodRepository);
    }

    @Test
    void savePersonalData() {
        // Given
        PersonalDataDao dao = aPersonalDataDao();

        // When
        useCase.savePersonalData(dao);

        // Then
        verify(personalDataRepository).save(dao);

        verifyNoMoreInteractions(personalDataRepository);
        verifyNoInteractions(addressRepository);
        verifyNoInteractions(paymentMethodRepository);
    }

    @Test
    void saveAddress() {
        // Given
        AddressDao dao = aAddressDao();

        // When
        useCase.saveAddress(dao);

        // Then
        verify(addressRepository).save(dao);

        verifyNoInteractions(personalDataRepository);
        verifyNoMoreInteractions(addressRepository);
        verifyNoInteractions(paymentMethodRepository);
    }

    @Test
    void savePaymentMethod() {
        // Given
        PaymentMethodDao dao = aPaymentMethodDao();

        // When
        useCase.savePaymentMethod(dao);

        // Then
        verify(paymentMethodRepository).save(dao);

        verifyNoInteractions(personalDataRepository);
        verifyNoInteractions(addressRepository);
        verifyNoMoreInteractions(paymentMethodRepository);
    }

    @Test
    void deleteAddressBy() {
        // Given
        String username = aText();
        long id = aLong();
        AddressDao dao = aAddressDao();
        dao.setUsername(username);
        dao.setId(id);

        when(addressRepository.findById(id)).thenReturn(Optional.of(dao));

        // When
        useCase.deleteAddress(id, username);

        // Then
        verify(addressRepository).delete(dao);

        verifyNoInteractions(personalDataRepository);
        verifyNoMoreInteractions(addressRepository);
        verifyNoInteractions(paymentMethodRepository);
    }

    @Test
    void deleteAddress_notAllowed() {
        // Given
        String username = aText();
        long id = aLong();
        AddressDao dao = aAddressDao();
        dao.setId(id);

        when(addressRepository.findById(id)).thenReturn(Optional.of(dao));

        // When
        // Then
        assertThrows(IllegalStateException.class, () -> useCase.deleteAddress(id, username));
    }

    @Test
    void deletePaymentMethod() {
        // Given
        String username = aText();
        long id = aLong();
        PaymentMethodDao dao = aPaymentMethodDao();
        dao.setUsername(username);
        dao.setId(id);

        when(paymentMethodRepository.findById(id)).thenReturn(Optional.of(dao));

        // When
        useCase.deletePaymentMethod(id, username);

        // Then
        verify(paymentMethodRepository).delete(dao);

        verifyNoInteractions(personalDataRepository);
        verifyNoInteractions(addressRepository);
        verifyNoMoreInteractions(paymentMethodRepository);
    }

    @Test
    void deletePaymentMethod_notAllowed() {
        // Given
        String username = aText();
        long id = aLong();
        PaymentMethodDao dao = aPaymentMethodDao();
        dao.setId(id);

        when(paymentMethodRepository.findById(id)).thenReturn(Optional.of(dao));

        // When
        // Then
        assertThrows(IllegalStateException.class, () -> useCase.deletePaymentMethod(id, username));
    }

}
