package co.usa.ciclo3.ciclo3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan(basePackages = {"co.usa.ciclo3.ciclo3.model"})
@SpringBootApplication
public class ciclo3Application {

	public static void main(String[] args) {
		SpringApplication.run(ciclo3Application.class, args);
	}

}
