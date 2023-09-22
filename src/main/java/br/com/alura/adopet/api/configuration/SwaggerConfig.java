package br.com.alura.adopet.api.configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI springFarmaciaOpenAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("Farmacia API")
                        .description("API para gerenciamento de PetSHOP")
                        .version("v0.0.1")
                        .termsOfService("http://swagger.io/terms")
                        .license(new License()
                                .name("Linkedin")
                                .url("https://www.linkedin.com/in/viniciusdsandrade/"))
                        .contact(new Contact()
                                .name("Vinicius dos Santos Andrade")
                                .url("https://github.com/viniciusdsandrade/Gen66-farmacia")
                                .email("vinicius_andrade2010@hotmail.com")))
                .externalDocs(new ExternalDocumentation()
                        .description("Documentação do projeto")
                        .url("https://github.com/viniciusdsandrade/"));
    }

    public ApiResponse createApiResponse(String message) {
        return new ApiResponse().description(message);
    }

    /**
     * Configura as respostas padrão para operações.
     *
     * @return Customizador OpenApiCustomizer para adicionar respostas padrão.
     */
    @Bean
    public OpenApiCustomizer customGlobalHeaderOpenApiCustomizer() {
        return openApi -> {
            openApi.getPaths().values().forEach(pathItem -> {
                for (Operation operation : pathItem.readOperations()) {
                    ApiResponses apiResponses = operation.getResponses();

                    // Códigos 2xx
                    apiResponses.addApiResponse("200", createApiResponse("Requisição bem-sucedida"));
                    apiResponses.addApiResponse("201", createApiResponse("Recurso criado com sucesso"));
                    apiResponses.addApiResponse("204", createApiResponse("Sem conteúdo"));
                    apiResponses.addApiResponse("206", createApiResponse("Conteúdo parcialmente fornecido"));
                    apiResponses.addApiResponse("207", createApiResponse("Status Multi-status"));

                    // Códigos 3xx
                    apiResponses.addApiResponse("300", createApiResponse("Múltiplas escolhas"));
                    apiResponses.addApiResponse("301", createApiResponse("Movido permanentemente"));
                    apiResponses.addApiResponse("302", createApiResponse("Encontrado"));

                    // Códigos 4xx
                    apiResponses.addApiResponse("400", createApiResponse("Requisição inválida"));
                    apiResponses.addApiResponse("401", createApiResponse("Não autorizado"));
                    apiResponses.addApiResponse("403", createApiResponse("Acesso negado"));
                    apiResponses.addApiResponse("404", createApiResponse("Não encontrado"));
                    apiResponses.addApiResponse("406", createApiResponse("Não Aceitável"));

                    // Códigos 5xx
                    apiResponses.addApiResponse("500", createApiResponse("Erro interno"));
                    apiResponses.addApiResponse("501", createApiResponse("Não implementado"));
                    apiResponses.addApiResponse("502", createApiResponse("Gateway ruim"));
                }
            });
        };
    }
}
