package fr.jrlelo.bnaiss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//Desactivation temporaire de la securite dans spring security avec :
// exclude = {SecurityAutoConfiguration.class,SecuredAuthorizationManager.class}
@SpringBootApplication
public class BnaissApplication {

    public static void main(String[] args) {
        SpringApplication.run(BnaissApplication.class, args);
    }

}
