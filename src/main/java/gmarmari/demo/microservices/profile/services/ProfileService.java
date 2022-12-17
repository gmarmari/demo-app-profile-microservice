package gmarmari.demo.microservices.profile.services;

import gmarmari.demo.microservices.profile.entities.AddressDao;
import gmarmari.demo.microservices.profile.entities.PaymentMethodDao;
import gmarmari.demo.microservices.profile.entities.PersonalDataDao;

import java.util.List;
import java.util.Optional;

public interface ProfileService {

    Optional<PersonalDataDao> getPersonalData(String username);

    List<AddressDao> getAddresses(String username);

    List<PaymentMethodDao> getPaymentMethods(String username);

    void savePersonalData(PersonalDataDao personalData);

    void saveAddress(AddressDao address);

    void savePaymentMethod(PaymentMethodDao paymentMethod);

    void deleteAddress(long addressId, String username);

    void deletePaymentMethod(long paymentMethodId, String username);

}
