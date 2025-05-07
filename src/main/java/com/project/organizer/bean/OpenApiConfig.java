package com.project.organizer.bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customizacao(){
        return new OpenAPI()
                .info(new Info()
                        .title("API Projetos")
                        .version("1.0.0")
                        .description("Documentação básica para consumo e gerenciamento da api"));
    }
}
