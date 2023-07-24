package com.example.Backend;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BackendApplication {
	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}
	@Bean
	public OpenAPI customOpenApi(){
		return new OpenAPI().info(new Info()
				.title("Swagger Ajedrez")
				.version("v.0.1")
				.termsOfService("url destino")
				.license(new License().name("License UTN")));
	}
}

