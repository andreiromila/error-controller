package error.controller;

import error.controller.error.ApiError;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class ApplicationTests {

	@Autowired
	TestRestTemplate restTemplate;

	@Test
	void postLogin_withInvalidCredentials_receiveApiError() {

		// Using this, will never get a body back
		restTemplate.getRestTemplate().getInterceptors().add(
				new BasicAuthenticationInterceptor("nonexistent", "")
		);

		ResponseEntity<ApiError> response = restTemplate.postForEntity("/login", null, ApiError.class);

		// This is ok
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);

		// NullPointerException here
		assertThat(response.getBody().getPath()).isEqualTo("/login");
	}

}
