package com.example.plantapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;

@SpringBootApplication
public class PlantappApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlantappApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder
				.additionalMessageConverters(new FormHttpMessageConverter(), new ByteArrayHttpMessageConverter())
				.build();
	}
}
