package gmarmari.demo.microservices.profile.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/profile")
@Tag(name = "Profile API", description = "User profile management API")
public interface ProfileApi {

    @GetMapping(path = "/personal-data", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            description = "Get the personal data of the current user"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PersonalDataDto.class))
            )

    })
    PersonalDataDto getPersonalData();

    @GetMapping(path = "/addresses", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            description = "List of addresses for the current user"
    )
    List<AddressDto> getAddresses();

    @GetMapping(path = "/payment-methods", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            description = "List of payment methods for the current user"
    )
    List<PaymentMethodDto> getPaymentMethods();

    @PostMapping(path = "/personal-data", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            description = "Save the given personal data"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Personal data were saved"),
            @ApiResponse(
                    responseCode = "500",
                    description = "An error occurred by saving the personal data")

    })
    void savePersonalData(@RequestBody PersonalDataDto personalData);

    @PostMapping(path = "/addresses", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            description = "Save the given address"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Address was saved"),
            @ApiResponse(
                    responseCode = "500",
                    description = "An error occurred by saving the address")

    })
    void saveAddress(@RequestBody AddressDto address);

    @PostMapping(path = "/payment-methods", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            description = "Save the given payment method"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Payment method was saved"),
            @ApiResponse(
                    responseCode = "500",
                    description = "An error occurred by saving the payment method")

    })
    void savePaymentMethod(@RequestBody PaymentMethodDto paymentMethod);

    @DeleteMapping(path = "/addresses/{addressId}")
    @Operation(
            description = "Delete the address with the given id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Address was deleted"),
            @ApiResponse(
                    responseCode = "500",
                    description = "An error occurred by deleting the address")

    })
    void deleteAddress(@PathVariable("addressId") long addressId);

    @DeleteMapping(path = "/payment-methods/{paymentMethodId}")
    @Operation(
            description = "Delete the payment method with the given id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Payment method was deleted"),
            @ApiResponse(
                    responseCode = "500",
                    description = "An error occurred by deleting the payment method")

    })
    void deletePaymentMethod(@PathVariable("paymentMethodId") long paymentMethodId);





}
