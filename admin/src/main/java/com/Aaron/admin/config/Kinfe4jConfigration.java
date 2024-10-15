package com.Aaron.admin.config;

import io.swagger.v3.oas.models.OpenAPI;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;

public class Kinfe4jConfigration {


    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new io.swagger.v3.oas.models.info.Info()
                        .title("共享视界")
                        .version("1.0")
                        .description("共享视界"));
    }

    @Bean
    public GroupedOpenApi movieInfoApi() {
        return GroupedOpenApi.builder()
                .group("电影")
                .pathsToMatch("/movieInfo/**")
                .build();
    }
}
