package gmarmari.demo.microservices.profile.services.usecase;

import gmarmari.demo.microservices.profile.entities.AddressDao;
import gmarmari.demo.microservices.profile.entities.PaymentMethodDao;
import gmarmari.demo.microservices.profile.entities.PersonalDataDao;
import gmarmari.demo.microservices.profile.repositories.AddressRepository;
import gmarmari.demo.microservices.profile.repositories.PaymentMethodRepository;
import gmarmari.demo.microservices.profile.repositories.PersonalDataRepository;
import gmarmari.demo.microservices.profile.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class ProfileUseCase implements ProfileService {

    private static final Sort ADDRESS_SORT = Sort.by("type").ascending();
    private static final Sort PAYMENT_METHOD_SORT = Sort.by("type").ascending();

    private final PersonalDataRepository personalDataRepository;
    private final AddressRepository addressRepository;
    private final PaymentMethodRepository paymentMethodRepository;

    @Autowired
    public ProfileUseCase(PersonalDataRepository personalDataRepository,
                          AddressRepository addressRepository,
                          PaymentMethodRepository paymentMethodRepository) {
        this.personalDataRepository = personalDataRepository;
        this.addressRepository = addressRepository;
        this.paymentMethodRepository = paymentMethodRepository;
    }

    @Override
    public Optional<PersonalDataDao> getPersonalData(String username) {
        return personalDataRepository.findByUsername(username);
    }

    @Override
    public List<AddressDao> getAddresses(String username) {
        return addressRepository.findByUsername(username, ADDRESS_SORT);
    }

    @Override
    public List<PaymentMethodDao> getPaymentMethods(String username) {
        return paymentMethodRepository.findByUsername(username, PAYMENT_METHOD_SORT);
    }

    @Override
    public void savePersonalData(PersonalDataDao personalData) {
        personalDataRepository.save(personalData);
    }

    @Override
    public void saveAddress(AddressDao address) {
        addressRepository.save(address);
    }

    @Override
    public void savePaymentMethod(PaymentMethodDao paymentMethod) {
        paymentMethodRepository.save(paymentMethod);
    }

    @Override
    public void deleteAddress(long addressId, String username) {
        addressRepository.findById(addressId).ifPresent(dao -> {
            if (Objects.equals(dao.getUsername(), username)) {
                addressRepository.delete(dao);
            } else {
                throw new IllegalStateException("Delete of address with id " + addressId + " is not allowed from user " + username);
            }
        });
    }

    @Override
    public void deletePaymentMethod(long paymentMethodId, String username) {
        paymentMethodRepository.findById(paymentMethodId).ifPresent(dao -> {
            if (Objects.equals(dao.getUsername(), username)) {
                paymentMethodRepository.delete(dao);
            } else {
                throw new IllegalStateException("Delete of payment method with id " + paymentMethodId + " is not allowed from user " + username);
            }
        });
    }
}
