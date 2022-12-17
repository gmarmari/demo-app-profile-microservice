package gmarmari.demo.microservices.profile.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Schema(name = "PaymentMethod_V01")
public class PaymentMethodDto {

    public final long id;

    @NotNull
    public final PaymentMethodTypeDto type;

    @Nullable
    @Size(max = 100)
    public final String text1;

    @Nullable
    @Size(max = 100)
    public final String text2;

    public PaymentMethodDto(@JsonProperty("id") long id,
                            @JsonProperty("type") PaymentMethodTypeDto type,
                            @Nullable @JsonProperty("text1")  String text1,
                            @Nullable @JsonProperty("text2")  String text2) {
        this.id = id;
        this.type = type;
        this.text1 = text1;
        this.text2 = text2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentMethodDto that = (PaymentMethodDto) o;
        return id == that.id && type == that.type && Objects.equals(text1, that.text1) && Objects.equals(text2, that.text2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, text1, text2);
    }

    @Override
    public String toString() {
        return "PaymentMethodDto{" +
                "id=" + id +
                ", type=" + type +
                ", text1='" + text1 + '\'' +
                ", text2='" + text2 + '\'' +
                '}';
    }
}
