package gmarmari.demo.microservices.profile.entities;

import com.sun.istack.NotNull;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "payment_method")
public class PaymentMethodDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String username;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private PaymentMethodTypeDao type;

    @Nullable
    private String text1;

    @Nullable
    private String text2;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public PaymentMethodTypeDao getType() {
        return type;
    }

    public void setType(PaymentMethodTypeDao type) {
        this.type = type;
    }

    @Nullable
    public String getText1() {
        return text1;
    }

    public void setText1(@Nullable String text1) {
        this.text1 = text1;
    }

    @Nullable
    public String getText2() {
        return text2;
    }

    public void setText2(@Nullable String text2) {
        this.text2 = text2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentMethodDao that = (PaymentMethodDao) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
