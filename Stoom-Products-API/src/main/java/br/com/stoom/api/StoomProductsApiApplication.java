package br.com.stoom.api;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Log4j2
@SpringBootApplication
public class StoomProductsApiApplication {
	public static void main(String[] args) {
		log.info("************* STARTING APPLICATION *************");
		SpringApplication.run(StoomProductsApiApplication.class, args);
		log.info("************* APPLICATION RUNNING *************");
	}
}
