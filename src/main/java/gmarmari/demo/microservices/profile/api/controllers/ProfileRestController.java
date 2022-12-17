package gmarmari.demo.microservices.profile.api.controllers;

import gmarmari.demo.microservices.profile.adapters.ProfileAdapter;
import gmarmari.demo.microservices.profile.api.AddressDto;
import gmarmari.demo.microservices.profile.api.PaymentMethodDto;
import gmarmari.demo.microservices.profile.api.PersonalDataDto;
import gmarmari.demo.microservices.profile.api.ProfileApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class ProfileRestController implements ProfileApi {

    private final ProfileAdapter adapter;

    @Autowired
    public ProfileRestController(ProfileAdapter adapter) {
        this.adapter = adapter;
    }



    @Override
    public PersonalDataDto getPersonalData() {
        return adapter.getPersonalData(getUsername());
    }

    @Override
    public List<AddressDto> getAddresses() {
        return adapter.getAddresses(getUsername());
    }

    @Override
    public List<PaymentMethodDto> getPaymentMethods() {
        return adapter.getPaymentMethods(getUsername());
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
