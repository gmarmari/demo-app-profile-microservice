package gmarmari.demo.microservices.profile.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Schema(name = "PersonalData_V01")
public class PersonalDataDto {

    public static final PersonalDataDto EMPTY = new PersonalDataDto(-1, SalutationDto.NONE, "", "", "", "");

    public final long id;

    @NotNull
    public final SalutationDto salutation;

    @NotNull
    @Size(max = 100)
    public final String firstName;

    @NotNull
    @Size(max = 100)
    public final String lastName;

    @NotNull
    @Size(max = 100)
    public final String email;

    @NotNull
    @Size(max = 100)
    public final String password;

    public PersonalDataDto(@JsonProperty("id") long id,
                           @JsonProperty("salutation") SalutationDto salutation,
                           @JsonProperty("firstName") String firstName,
                           @JsonProperty("lastName") String lastName,
                           @JsonProperty("email") String email,
                           @JsonProperty("password") String password) {
        this.id = id;
        this.salutation = salutation;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonalDataDto that = (PersonalDataDto) o;
        return id == that.id && salutation == that.salutation && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(email, that.email) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, salutation, firstName, lastName, email, password);
    }

    @Override
    public String toString() {
        return "PersonalDataDto{" +
                "id=" + id +
                ", salutation=" + salutation +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
