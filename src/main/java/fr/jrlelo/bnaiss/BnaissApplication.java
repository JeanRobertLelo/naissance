package fr.jrlelo.bnaiss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.security.autoconfigure.SecurityAutoConfiguration;
import org.springframework.security.authorization.method.SecuredAuthorizationManager;

//Desactivation temporaire de la securite dans spring security avec :
// exclude = {SecurityAutoConfiguration.class,SecuredAuthorizationManager.class}
@SpringBootApplication(
		exclude = {
				SecurityAutoConfiguration.class,
				SecuredAuthorizationManager.class

		})
public class BnaissApplication {

	public static void main(String[] args) {
		SpringApplication.run(BnaissApplication.class, args);
	}

}
