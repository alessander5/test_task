package com.test.rest.spring_boot;

import org.hibernate.mapping.List;
import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void tokenTest() {
		ResponseEntity<String> entity = this.restTemplate.getForEntity("/token?login=admin&password=admin", String.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	public void unAuthTest() throws Exception{
		ResponseEntity<List> entity = this.restTemplate.getForEntity("/user", List.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
	}



}
