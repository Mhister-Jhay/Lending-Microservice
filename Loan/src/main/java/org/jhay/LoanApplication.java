package org.jhay;

import lombok.RequiredArgsConstructor;
import org.jhay.domain.enums.Gender;
import org.jhay.domain.model.User;
import org.jhay.domain.repository.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LoanApplication{

	public static void main(String[] args) {
		SpringApplication.run(LoanApplication.class, args);
	}

}
