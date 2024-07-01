package com.bet.BettingGame;

//import com.bet.BettingGame.SSL.SSLCertificateValidationDisabler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
//
//import javax.annotation.PostConstruct;


@SpringBootApplication
//@ComponentScan("com.bet.BettingGame.service")
public class BettingGameApplication {

	public static void main(String[] args) {
		SpringApplication.run(BettingGameApplication.class, args);
	}
//		@PostConstruct
//		public void disableSSLValidation() {
//			SSLCertificateValidationDisabler.disable();
//		}


}
