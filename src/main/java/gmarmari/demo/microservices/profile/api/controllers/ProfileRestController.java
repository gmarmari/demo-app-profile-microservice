package gmarmari.demo.microservices.profile.api.controllers;

import gmarmari.demo.microservices.profile.adapters.ProfileAdapter;
import gmarmari.demo.microservices.profile.api.AddressDto;
import gmarmari.demo.microservices.profile.api.PaymentMethodDto;
import gmarmari.demo.microservices.profile.api.PersonalDataDto;
import gmarmari.demo.microservices.profile.api.ProfileApi;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Random;

@RestController
public class ProfileRestController implements ProfileApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProfileRestController.class);
    private final ProfileAdapter adapter;

    @Autowired
    public ProfileRestController(ProfileAdapter adapter) {
        this.adapter = adapter;
    }



    @Override
    @CircuitBreaker(name = "breaker", fallbackMethod = "getPersonalDataFallback")
    public PersonalDataDto getPersonalData() {
        return adapter.getPersonalData(getUsername());
    }

    public PersonalDataDto getPersonalDataFallback(Throwable t) {
        LOGGER.warn("Fallback method for getPersonalData", t);
        return PersonalDataDto.EMPTY;
    }

    @Override
    @CircuitBreaker(name = "breaker", fallbackMethod = "getAddressesFallback")
    public List<AddressDto> getAddresses() {
        return adapter.getAddresses(getUsername());
    }

    public List<AddressDto> getAddressesFallback(Throwable t) {
        LOGGER.warn("Fallback method for getAddresses", t);
        return List.of();
    }

    @Override
    @CircuitBreaker(name = "breaker", fallbackMethod = "getPaymentMethodsFallback")
    public List<PaymentMethodDto> getPaymentMethods() {
        return adapter.getPaymentMethods(getUsername());
    }

    public List<AddressDto> getPaymentMethodsFallback(Throwable t) {
        LOGGER.warn("Fallback method for getPaymentMethods", t);
        return List.of();
    }

    @Override
    public void savePersonalData(PersonalDataDto personalData) {
        adapter.savePersonalData(personalData, getUsername())
                .throwIfError(() -> new ResponseStatusException(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        "An error occurred by saving the personal data"));
    }

    @Override
    public void saveAddress(AddressDto address) {
        adapter.saveAddress(address, getUsername())
                .throwIfError(() -> new ResponseStatusException(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        "An error occurred by saving the address"));
    }

    @Override
    public void savePaymentMethod(PaymentMethodDto paymentMethod) {
        adapter.savePaymentMethod(paymentMethod, getUsername())
                .throwIfError(() -> new ResponseStatusException(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        "An error occurred by saving the payment method"));
    }

    @Override
    public void deleteAddress(long addressId) {
        adapter.deleteAddress(addressId, getUsername())
                .throwIfError(() -> new ResponseStatusException(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        "An error occurred by deleting the address"));
    }

    @Override
    public void deletePaymentMethod(long paymentMethodId) {
        adapter.deletePaymentMethod(paymentMethodId, getUsername())
                .throwIfError(() -> new ResponseStatusException(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        "An error occurred by deleting the payment method"));
    }


    private String getUsername() {
        // Authentication is not implemented
        return "super_user";
    }
}
