package it.alessiorossato.sagra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableResourceServer
public class SagraApplication {

	public static void main(String[] args) {
		SpringApplication.run(SagraApplication.class, args);
	}

}

