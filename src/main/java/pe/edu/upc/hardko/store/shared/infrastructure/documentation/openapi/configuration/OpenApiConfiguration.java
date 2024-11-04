package pe.edu.upc.hardko.store.shared.infrastructure.documentation.openapi.configuration;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class OpenApiConfiguration {
    @Bean
    public OpenAPI learningPlatformOpenApi() {
        // General configuration
        var openApi = new OpenAPI();
        openApi
                .info(new Info()
                        .title("HardKo Store API")
                        .description("HardKo Store application REST API documentation.")
                        .version("v0.1.0")
                        .license(new License().name("Apache 2.0")
                                .url("https://springdoc.org")));
        return openApi;
    }
}
