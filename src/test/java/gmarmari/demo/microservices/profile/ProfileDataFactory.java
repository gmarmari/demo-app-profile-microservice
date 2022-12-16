package gmarmari.demo.microservices.profile;

import gmarmari.demo.microservices.profile.entities.*;

import static gmarmari.demo.microservices.profile.CommonDataFactory.*;

@SuppressWarnings("unused")
public class ProfileDataFactory {

    private ProfileDataFactory() {
        // Hide constructor
    }

    //region dao

    public static AddressDao aAddressDao() {
        return aAddressDao(false);
    }

    public static AddressDao aAddressDao(boolean withId) {
        AddressDao dao = new AddressDao();
        if (withId) {
            dao.setId(aLong());
        }
        dao.setUsername(aText());

        int index = aInt(AddressTypeDao.values().length);
        dao.setType(AddressTypeDao.values()[index]);

        dao.setName(aText());
        dao.setStreet(aText());
        dao.setPostalCode(aText());
        dao.setCity(aText());
        dao.setState(aNullableText());
        dao.setCountry(aText());
        dao.setTel(aNullableText());

        return dao;
    }

    public static PaymentMethodDao aPaymentMethodDao() {
        return aPaymentMethodDao(false);
    }

    public static PaymentMethodDao aPaymentMethodDao(boolean withId) {
        PaymentMethodDao dao = new PaymentMethodDao();
        if (withId) {
            dao.setId(aLong());
        }

        dao.setUsername(aText());

        int index = aInt(PaymentMethodTypeDao.values().length);
        dao.setType(PaymentMethodTypeDao.values()[index]);

        dao.setText1(aNullableText());
        dao.setText2(aNullableText());
        return dao;
    }

    public static PersonalDataDao aPersonalDataDao() {
        return aPersonalDataDao(false);
    }

    public static PersonalDataDao aPersonalDataDao(boolean withId) {
        PersonalDataDao dao = new PersonalDataDao();
        if (withId) {
            dao.setId(aLong());
        }

        dao.setUsername(aText());

        int index = aInt(SalutationDao.values().length);
        dao.setSalutation(SalutationDao.values()[index]);

        dao.setFirstName(aText());
        dao.setLastName(aText());
        dao.setEmail(aText());
        dao.setPassword(aText());
        return dao;
    }

    //endregion dao


}
