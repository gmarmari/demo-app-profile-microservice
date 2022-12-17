package gmarmari.demo.microservices.profile.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Schema(name = "Address_V01")
public class AddressDto {

    public final long id;

    @NotNull
    public final AddressTypeDto type;

    @NotBlank
    @Size(max = 100)
    public final String name;

    @NotBlank
    @Size(max = 100)
    public final String street;

    @NotBlank
    @Size(max = 100)
    public final String postalCode;

    @NotBlank
    @Size(max = 100)
    public final String city;

    @Nullable
    @Size(max = 100)
    public final String state;

    @NotBlank
    @Size(max = 100)
    public final String country;

    @Nullable
    @Size(max = 100)
    public final String tel;

    public AddressDto(@JsonProperty("id") long id,
                      @JsonProperty("type") AddressTypeDto type,
                      @JsonProperty("name") String name,
                      @JsonProperty("street") String street,
                      @JsonProperty("postalCode") String postalCode,
                      @JsonProperty("city") String city,
                      @Nullable @JsonProperty("state") String state,
                      @JsonProperty("country") String country,
                      @Nullable @JsonProperty("tel") String tel) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.street = street;
        this.postalCode = postalCode;
        this.city = city;
        this.state = state;
        this.country = country;
        this.tel = tel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressDto that = (AddressDto) o;
        return id == that.id && type == that.type && Objects.equals(name, that.name) && Objects.equals(street, that.street) && Objects.equals(postalCode, that.postalCode) && Objects.equals(city, that.city) && Objects.equals(state, that.state) && Objects.equals(country, that.country) && Objects.equals(tel, that.tel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, name, street, postalCode, city, state, country, tel);
    }

    @Override
    public String toString() {
        return "AddressDto{" +
                "id=" + id +
                ", type=" + type +
                ", name='" + name + '\'' +
                ", street='" + street + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", tel='" + tel + '\'' +
                '}';
    }
}
