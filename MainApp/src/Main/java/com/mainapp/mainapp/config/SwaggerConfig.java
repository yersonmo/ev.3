package com.mainapp.mainapp.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.*;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API EduTech Innovators")
                        .version("1.0")
                        .description("Documentación de la API para la gestión académica (alumnos, cursos, profesores)")
                        .contact(new Contact()
                                .name("Equipo EduTech")
                                .email("soporte@edutech.cl")
                                .url("https://edutech.cl"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")))
                .tags(List.of(
                        new Tag().name("Alumnos").description("Operaciones sobre estudiantes de la plataforma"),
                        new Tag().name("Cursos").description("Gestión y detalle de cursos ofrecidos"),
                        new Tag().name("Profesores").description("Gestión de profesores asociados a los cursos")
                ));
    }
}