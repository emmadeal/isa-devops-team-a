package fr.univcotedazur.multifidelity.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(apiInfo());
    }

    private Info apiInfo() {
        return new Info().title("Multi fidelity API definition")
                .description("Multi fidelity V1 : Le serveur permettant la gestion opérationnelle de la platforme Multi fidelity.")
                .version("1.0.0")
                .contact(new Contact().name("Marini Claire")
                        .email("clairemarini06390@gmail.com"));
    }


}
