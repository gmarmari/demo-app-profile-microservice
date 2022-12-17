package gmarmari.demo.microservices.profile.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@OpenAPIDefinition(
        info = @Info(
                title = "User Profile API",
                version = "V01",
                description = "Manages the profile of a user",
                contact = @Contact(
                        name = "Georgios",
                        url = "https://github.com/gmarmari/"
                ),
                license = @License(
                        name = "MIT Licence",
                        url = "https://opensource.org/licenses/MIT")
        )
)
public class OpenApiConfiguration {
}
