package id.ac.binus.programming.kost.user.common;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .paths(Predicates.not(PathSelectors.regex("/error/*")))
                .paths(Predicates.not(PathSelectors.regex("/actuator")))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact(
                "Kelompok tugas binus",
                "menyusul",
                "muhammad.erlangga@binus.ac.id");
        return new ApiInfo(
                "Tugas Programming Bpk. YANTO SETIAWAN, S.KOM., M.T.I., CEH, CIPMP",
                "Ini adalah swagger dari tugas membuat aplikasi java",
                "Version 0.0.1",
                "",
                contact,
                "Gratis",
                "Gratis",
                Collections.emptyList());
    }
}