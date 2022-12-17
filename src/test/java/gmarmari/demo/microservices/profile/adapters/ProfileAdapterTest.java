package gmarmari.demo.microservices.profile.adapters;


import gmarmari.demo.microservices.profile.api.AddressDto;
import gmarmari.demo.microservices.profile.api.PaymentMethodDto;
import gmarmari.demo.microservices.profile.api.PersonalDataDto;
import gmarmari.demo.microservices.profile.api.Response;
import gmarmari.demo.microservices.profile.entities.AddressDao;
import gmarmari.demo.microservices.profile.entities.PaymentMethodDao;
import gmarmari.demo.microservices.profile.entities.PersonalDataDao;
import gmarmari.demo.microservices.profile.services.ProfileService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static gmarmari.demo.microservices.profile.CommonDataFactory.aLong;
import static gmarmari.demo.microservices.profile.CommonDataFactory.aText;
import static gmarmari.demo.microservices.profile.ProfileDataFactory.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProfileAdapterTest {

    @Mock
    private ProfileService service;

    @Captor
    private ArgumentCaptor<PersonalDataDao> personalDataDaoCaptor;

    @Captor
    private ArgumentCaptor<AddressDao> addressDaoCaptor;

    @Captor
    private ArgumentCaptor<PaymentMethodDao> paymentMethodDaoCaptor;

    @InjectMocks
    private ProfileAdapter adapter;

    @Test
    void getPersonalData() {
        // Given
        String username = aText();
        PersonalDataDao dao = aPersonalDataDao(true);
        dao.setUsername(username);

        when(service.getPersonalData(username)).thenReturn(Optional.of(dao));

        // When
        PersonalDataDto result = adapter.getPersonalData(username);

        // Then
        verifyPersonalData(result, dao);
        verifyNoMoreInteractions(service);
    }

    @Test
    void getPersonalData_whenNotFound_thenEmpty() {
        // Given
        String username = aText();

        when(service.getPersonalData(username)).thenReturn(Optional.empty());

        // When
        PersonalDataDto result = adapter.getPersonalData(username);

        // Then
        assertThat(result).isEqualTo(PersonalDataDto.EMPTY);
        verifyNoMoreInteractions(service);
    }

    @Test
    void getAddresses() {
        // Given
        String username = aText();
        AddressDao daoA = aAddressDao(true);
        daoA.setUsername(username);
        AddressDao daoB = aAddressDao(true);
        daoB.setUsername(username);
        AddressDao daoC = aAddressDao(true);
        daoC.setUsername(username);

        when(service.getAddresses(username)).thenReturn(List.of(daoA, daoB, daoC));

        // When
        List<AddressDto> list = adapter.getAddresses(username);

        // Then
        assertThat(list).hasSize(3);
        verifyAddress(list.get(0), daoA);
        verifyAddress(list.get(1), daoB);
        verifyAddress(list.get(2), daoC);
        verifyNoMoreInteractions(service);
    }

    @Test
    void getPaymentMethods() {
        // Given
        String username = aText();
        PaymentMethodDao daoA = aPaymentMethodDao(true);
        daoA.setUsername(username);
        PaymentMethodDao daoB = aPaymentMethodDao(true);
        daoB.setUsername(username);
        PaymentMethodDao daoC = aPaymentMethodDao(true);
        daoC.setUsername(username);

        when(service.getPaymentMethods(username)).thenReturn(List.of(daoA, daoB, daoC));

        // When
        List<PaymentMethodDto> list = adapter.getPaymentMethods(username);

        // Then
        assertThat(list).hasSize(3);
        verifyPaymentMethod(list.get(0), daoA);
        verifyPaymentMethod(list.get(1), daoB);
        verifyPaymentMethod(list.get(2), daoC);
        verifyNoMoreInteractions(service);
    }

    @Test
    void savePersonalData() {
        // Given
        String username = aText();
        PersonalDataDto dto = aPersonalDataDto();

        // When
        Response result = adapter.savePersonalData(dto, username);

        // Then
        assertThat(result).isEqualTo(Response.OK);
        verify(service).savePersonalData(personalDataDaoCaptor.capture());
        PersonalDataDao dao = personalDataDaoCaptor.getValue();
        verifyPersonalData(dto, dao);
        assertThat(dao.getUsername()).isEqualTo(username);

        verifyNoMoreInteractions(service);
    }

    @Test
    void savePersonalData_error() {
        // Given
        String username = aText();
        PersonalDataDto dto = aPersonalDataDto();

        doThrow(new NullPointerException()).when(service).savePersonalData(any());

        // When
        Response result = adapter.savePersonalData(dto, username);

        // Then
        assertThat(result).isEqualTo(Response.ERROR);
        verifyNoMoreInteractions(service);
    }

    @Test
    void saveAddress() {
        // Given
        String username = aText();
        AddressDto dto = aAddressDto();

        // When
        Response result = adapter.saveAddress(dto, username);

        // Then
        assertThat(result).isEqualTo(Response.OK);
        verify(service).saveAddress(addressDaoCaptor.capture());
        AddressDao dao = addressDaoCaptor.getValue();
        verifyAddress(dto, dao);
        assertThat(dao.getUsername()).isEqualTo(username);

        verifyNoMoreInteractions(service);
    }

    @Test
    void saveAddress_error() {
        // Given
        String username = aText();
        AddressDto dto = aAddressDto();

        doThrow(new NullPointerException()).when(service).saveAddress(any());

        // When
        Response result = adapter.saveAddress(dto, username);

        // Then
        assertThat(result).isEqualTo(Response.ERROR);
        verifyNoMoreInteractions(service);
    }

    @Test
    void savePaymentMethod() {
        // Given
        String username = aText();
        PaymentMethodDto dto = aPaymentMethodDto();

        // When
        Response result = adapter.savePaymentMethod(dto, username);

        // Then
        assertThat(result).isEqualTo(Response.OK);
        verify(service).savePaymentMethod(paymentMethodDaoCaptor.capture());
        PaymentMethodDao dao = paymentMethodDaoCaptor.getValue();
        verifyPaymentMethod(dto, dao);
        assertThat(dao.getUsername()).isEqualTo(username);

        verifyNoMoreInteractions(service);
    }

    @Test
    void savePaymentMethod_error() {
        // Given
        String username = aText();
        PaymentMethodDto dto = aPaymentMethodDto();

        doThrow(new NullPointerException()).when(service).savePaymentMethod(any());

        // When
        Response result = adapter.savePaymentMethod(dto, username);

        // Then
        assertThat(result).isEqualTo(Response.ERROR);
        verifyNoMoreInteractions(service);
    }

    @Test
    void deleteAddress() {
        // Given
        String username = aText();
        long id = aLong();

        // When
        Response result = adapter.deleteAddress(id, username);

        // Then
        assertThat(result).isEqualTo(Response.OK);
        verify(service).deleteAddress(id, username);
        verifyNoMoreInteractions(service);
    }

    @Test
    void deleteAddress_error() {
        // Given
        String username = aText();
        long id = aLong();

        doThrow(new NullPointerException()).when(service).deleteAddress(id, username);

        // When
        Response result = adapter.deleteAddress(id, username);

        // Then
        assertThat(result).isEqualTo(Response.ERROR);
        verifyNoMoreInteractions(service);
    }

    @Test
    void deletePaymentMethod() {
        // Given
        String username = aText();
        long id = aLong();

        // When
        Response result = adapter.deletePaymentMethod(id, username);

        // Then
        assertThat(result).isEqualTo(Response.OK);
        verify(service).deletePaymentMethod(id, username);
        verifyNoMoreInteractions(service);
    }

    @Test
    void deletePaymentMethod_error() {
        // Given
        String username = aText();
        long id = aLong();

        doThrow(new NullPointerException()).when(service).deletePaymentMethod(id, username);

        // When
        Response result = adapter.deletePaymentMethod(id, username);

        // Then
        assertThat(result).isEqualTo(Response.ERROR);
        verifyNoMoreInteractions(service);
    }



    private void verifyPersonalData(PersonalDataDto dto, PersonalDataDao dao) {
        assertThat(dto.id).isEqualTo(dao.getId());
        assertThat(dto.salutation.name()).isEqualTo(dao.getSalutation().name());
        assertThat(dto.firstName).isEqualTo(dao.getFirstName());
        assertThat(dto.lastName).isEqualTo(dao.getLastName());
        assertThat(dto.email).isEqualTo(dao.getEmail());
        assertThat(dto.password).isEqualTo(dao.getPassword());
    }

    private void verifyAddress(AddressDto dto, AddressDao dao) {
        assertThat(dto.id).isEqualTo(dao.getId());
        assertThat(dto.type.name()).isEqualTo(dao.getType().name());
        assertThat(dto.name).isEqualTo(dao.getName());
        assertThat(dto.street).isEqualTo(dao.getStreet());
        assertThat(dto.postalCode).isEqualTo(dao.getPostalCode());
        assertThat(dto.city).isEqualTo(dao.getCity());
        assertThat(dto.state).isEqualTo(dao.getState());
        assertThat(dto.country).isEqualTo(dao.getCountry());
        assertThat(dto.tel).isEqualTo(dao.getTel());
    }

    private void verifyPaymentMethod(PaymentMethodDto dto, PaymentMethodDao dao) {
        assertThat(dto.id).isEqualTo(dao.getId());
        assertThat(dto.type.name()).isEqualTo(dao.getType().name());
        assertThat(dto.text1).isEqualTo(dao.getText1());
        assertThat(dto.text2).isEqualTo(dao.getText2());
    }
}