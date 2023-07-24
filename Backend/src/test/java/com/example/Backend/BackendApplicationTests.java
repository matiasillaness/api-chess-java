package com.example.Backend;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BackendApplicationTests {

	@Test
	void contextLoads() {
	}
	@Test
	void testMainMethodExecution() {
		BackendApplication.main(new String[]{});
	}
	@Test
	void testCustomOpenApi() {
		OpenAPI expectedOpenAPI = new OpenAPI().info(new Info()
				.title("Swagger Ajedrez")
				.version("v.0.1")
				.termsOfService("url destino")
				.license(new License().name("License UTN")));
		OpenAPI generatedOpenAPI = new OpenAPI().info(new Info()
				.title("Swagger Ajedrez")
				.version("v.0.1")
				.termsOfService("url destino")
				.license(new License().name("License UTN")));
		Assertions.assertEquals(expectedOpenAPI.getInfo().getTitle(), generatedOpenAPI.getInfo().getTitle());
		Assertions.assertEquals(expectedOpenAPI.getInfo().getVersion(), generatedOpenAPI.getInfo().getVersion());
		Assertions.assertEquals(expectedOpenAPI.getInfo().getTermsOfService(), generatedOpenAPI.getInfo().getTermsOfService());
		Assertions.assertEquals(expectedOpenAPI.getInfo().getLicense().getName(), generatedOpenAPI.getInfo().getLicense().getName());
	}
}
