package gmarmari.demo.microservices.profile.api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import gmarmari.demo.microservices.profile.adapters.ProfileAdapter;
import gmarmari.demo.microservices.profile.api.AddressDto;
import gmarmari.demo.microservices.profile.api.PaymentMethodDto;
import gmarmari.demo.microservices.profile.api.PersonalDataDto;
import gmarmari.demo.microservices.profile.api.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static gmarmari.demo.microservices.profile.CommonDataFactory.aLong;
import static gmarmari.demo.microservices.profile.ProfileDataFactory.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ProfileRestController.class)
class ProfileRestControllerTest {

    private static final String USER_NAME = "super_user";

    @MockBean
    private ProfileAdapter adapter;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Test
    void getPersonalData() throws Exception {
        // Given
        PersonalDataDto dto = aPersonalDataDto();
        when(adapter.getPersonalData(USER_NAME)).thenReturn(dto);

        // When
        ResultActions resultActions = mockMvc.perform(get("/profile/personal-data"));

        // Then
        resultActions.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(dto)));
    }

    @Test
    void getAddresses() throws Exception {
        // Given
        List<AddressDto> list = List.of(aAddressDto(), aAddressDto(), aAddressDto());
        when(adapter.getAddresses(USER_NAME)).thenReturn(list);

        // When
        ResultActions resultActions = mockMvc.perform(get("/profile/addresses"));

        // Then
        resultActions.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(list)));
    }

    @Test
    void getPaymentMethods() throws Exception {
        // Given
        List<PaymentMethodDto> list = List.of(aPaymentMethodDto(), aPaymentMethodDto(), aPaymentMethodDto());
        when(adapter.getPaymentMethods(USER_NAME)).thenReturn(list);

        // When
        ResultActions resultActions = mockMvc.perform(get("/profile/payment-methods"));

        // Then
        resultActions.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(list)));
    }

    @Test
    void savePersonalData() throws Exception {
        // Given
        PersonalDataDto dto = aPersonalDataDto();

        when(adapter.savePersonalData(dto, USER_NAME)).thenReturn(Response.OK);

        // When
        ResultActions resultActions = mockMvc.perform(post("/profile/personal-data").
                contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(dto)));

        // Then
        resultActions.andExpect(status().isOk());
    }

    @Test
    void savePersonalData_error() throws Exception {
        // Given
        PersonalDataDto dto = aPersonalDataDto();

        when(adapter.savePersonalData(dto, USER_NAME)).thenReturn(Response.ERROR);

        // When
        ResultActions resultActions = mockMvc.perform(post("/profile/personal-data").
                contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(dto)));

        // Then
        resultActions.andExpect(status().isInternalServerError());
    }

    @Test
    void saveAddress() throws Exception {
        // Given
        AddressDto dto = aAddressDto();

        when(adapter.saveAddress(dto, USER_NAME)).thenReturn(Response.OK);

        // When
        ResultActions resultActions = mockMvc.perform(post("/profile/addresses").
                contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(dto)));

        // Then
        resultActions.andExpect(status().isOk());
    }

    @Test
    void saveAddress_error() throws Exception {
        // Given
        AddressDto dto = aAddressDto();

        when(adapter.saveAddress(dto, USER_NAME)).thenReturn(Response.ERROR);

        // When
        ResultActions resultActions = mockMvc.perform(post("/profile/addresses").
                contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(dto)));

        // Then
        resultActions.andExpect(status().isInternalServerError());
    }

    @Test
    void savePaymentMethod() throws Exception {
        // Given
        PaymentMethodDto dto = aPaymentMethodDto();

        when(adapter.savePaymentMethod(dto, USER_NAME)).thenReturn(Response.OK);

        // When
        ResultActions resultActions = mockMvc.perform(post("/profile/payment-methods").
                contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(dto)));

        // Then
        resultActions.andExpect(status().isOk());
    }

    @Test
    void savePaymentMethod_error() throws Exception {
        // Given
        PaymentMethodDto dto = aPaymentMethodDto();

        when(adapter.savePaymentMethod(dto, USER_NAME)).thenReturn(Response.ERROR);

        // When
        ResultActions resultActions = mockMvc.perform(post("/profile/payment-methods").
                contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(dto)));

        // Then
        resultActions.andExpect(status().isInternalServerError());
    }

    @Test
    void deleteAddress() throws Exception {
        // Given
        long addressId = aLong();
        when(adapter.deleteAddress(addressId, USER_NAME)).thenReturn(Response.OK);

        // When
        ResultActions resultActions = mockMvc.perform(delete("/profile/addresses/{addressId}", addressId));

        // Then
        resultActions.andExpect(status().isOk());
    }

    @Test
    void deleteById_error() throws Exception {
        // Given
        long addressId = aLong();
        when(adapter.deleteAddress(addressId, USER_NAME)).thenReturn(Response.ERROR);

        // When
        ResultActions resultActions = mockMvc.perform(delete("/profile/addresses/{addressId}", addressId));

        // Then
        resultActions.andExpect(status().isInternalServerError());
    }

    @Test
    void deletePaymentMethod() throws Exception {
        // Given
        long paymentMethodId = aLong();
        when(adapter.deletePaymentMethod(paymentMethodId, USER_NAME)).thenReturn(Response.OK);

        // When
        ResultActions resultActions = mockMvc.perform(delete("/profile/payment-methods/{paymentMethodId}", paymentMethodId));

        // Then
        resultActions.andExpect(status().isOk());
    }

    @Test
    void deletePaymentMethod_error() throws Exception {
        // Given
        long paymentMethodId = aLong();
        when(adapter.deletePaymentMethod(paymentMethodId, USER_NAME)).thenReturn(Response.ERROR);

        // When
        ResultActions resultActions = mockMvc.perform(delete("/profile/payment-methods/{paymentMethodId}", paymentMethodId));

        // Then
        resultActions.andExpect(status().isInternalServerError());
    }




}