package de.lhug;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;

@SpringBootApplication(exclude = { EmbeddedMongoAutoConfiguration.class })
public class RpDashApplication {

	protected RpDashApplication() {
		//implicit constructor
	}
	
	public static void main(String[] args) {
		SpringApplication.run(RpDashApplication.class, args);
	}
}
