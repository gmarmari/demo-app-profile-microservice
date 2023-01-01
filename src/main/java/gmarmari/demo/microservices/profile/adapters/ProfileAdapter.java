package gmarmari.demo.microservices.profile.adapters;

import gmarmari.demo.microservices.profile.api.*;
import gmarmari.demo.microservices.profile.entities.*;
import gmarmari.demo.microservices.profile.services.ProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProfileAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProfileAdapter.class);

    private final ProfileService service;

    @Autowired
    public ProfileAdapter(ProfileService service) {
        this.service = service;
    }


    public PersonalDataDto getPersonalData(String username) {
        return service.getPersonalData(username)
                .map(this::convert).orElse(PersonalDataDto.EMPTY);
    }

    public List<AddressDto> getAddresses(String username) {
        return service.getAddresses(username).stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

    public List<PaymentMethodDto> getPaymentMethods(String username) {
        return service.getPaymentMethods(username).stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

    public Response savePersonalData(PersonalDataDto personalData, String username) {
        try {
            service.savePersonalData(convert(personalData, username));
            return Response.OK;
        } catch (Exception e) {
            LOGGER.error("Error saving personal data", e);
            return Response.ERROR;
        }

    }

    public Response saveAddress(AddressDto address, String username){
        try {
            service.saveAddress(convert(address, username));
            return Response.OK;
        } catch (Exception e) {
            LOGGER.error("Error saving address", e);
            return Response.ERROR;
        }
    }

    public Response savePaymentMethod(PaymentMethodDto paymentMethod, String username) {
        try {
            service.savePaymentMethod(convert(paymentMethod, username));
            return Response.OK;
        } catch (Exception e) {
            LOGGER.error("Error saving payment method", e);
            return Response.ERROR;
        }
    }

    public Response deleteAddress(long addressId, String username) {
        try {
            service.deleteAddress(addressId, username);
            return Response.OK;
        } catch (Exception e) {
            LOGGER.error("Error deleting address with id " + addressId, e);
            return Response.ERROR;
        }
    }

    public Response deletePaymentMethod(long paymentMethodId, String username) {
        try {
            service.deletePaymentMethod(paymentMethodId, username);
            return Response.OK;
        } catch (Exception e) {
            LOGGER.error("Error deleting payment method with id " + paymentMethodId, e);
            return Response.ERROR;
        }
    }



    //region converters

    private PersonalDataDto convert(PersonalDataDao dao) {
        return new PersonalDataDto(
                dao.getId(),
                SalutationDto.valueOf(dao.getSalutation().name()),
                dao.getFirstName(),
                dao.getLastName(),
                dao.getEmail()
        );
    }

    private AddressDto convert(AddressDao dao) {
        return new AddressDto(
                dao.getId(),
                AddressTypeDto.valueOf(dao.getType().name()),
                dao.getName(),
                dao.getStreet(),
                dao.getPostalCode(),
                dao.getCity(),
                dao.getState(),
                dao.getCountry(),
                dao.getTel()
        );
    }

    private PaymentMethodDto convert(PaymentMethodDao dao) {
        return new PaymentMethodDto(
                dao.getId(),
                PaymentMethodTypeDto.valueOf(dao.getType().name()),
                dao.getText1(),
                dao.getText2()
        );
    }

    private PersonalDataDao convert(PersonalDataDto dto, String username) {
        PersonalDataDao dao = new PersonalDataDao();
        dao.setId(dto.id);
        dao.setUsername(username);
        dao.setSalutation(SalutationDao.valueOf(dto.salutation.name()));
        dao.setFirstName(dto.firstName);
        dao.setLastName(dto.lastName);
        dao.setEmail(dto.email);
        return dao;
    }

    private AddressDao convert(AddressDto dto, String username) {
        AddressDao dao = new AddressDao();
        dao.setId(dto.id);
        dao.setUsername(username);
        dao.setType(AddressTypeDao.valueOf(dto.type.name()));
        dao.setName(dto.name);
        dao.setStreet(dto.street);
        dao.setPostalCode(dto.postalCode);
        dao.setCity(dto.city);
        dao.setState(dto.state);
        dao.setCountry(dto.country);
        dao.setTel(dto.tel);
        return dao;
    }


    private PaymentMethodDao convert(PaymentMethodDto dto, String username) {
        PaymentMethodDao dao = new PaymentMethodDao();
        dao.setId(dto.id);
        dao.setUsername(username);
        dao.setType(PaymentMethodTypeDao.valueOf(dto.type.name()));
        dao.setText1(dto.text1);
        dao.setText2(dto.text2);
        return dao;
    }

    //endregion converters

}
