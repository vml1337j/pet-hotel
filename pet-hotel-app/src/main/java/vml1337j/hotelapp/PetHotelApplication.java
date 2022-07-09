package vml1337j.hotelapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class PetHotelApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetHotelApplication.class, args);
	}

}
