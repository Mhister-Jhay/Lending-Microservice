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
//@RequiredArgsConstructor
public class LoanApplication{
//	private final UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(LoanApplication.class, args);
	}

//	@Override
//	public void run(ApplicationArguments args) throws Exception {
//		userRepository.save(User.builder()
//						.firstName("joshua")
//						.lastName("omosigho")
//						.email("omosighojosh@gmail.com")
//						.isAccountSaved(true)
//						.isEmploymentSaved(true)
//						.isAddressSaved(true)
//						.phoneNumber("08095727920")
//						.gender(Gender.MALE)
//						.build());
//	}
}
