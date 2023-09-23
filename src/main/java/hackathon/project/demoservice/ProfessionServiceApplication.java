package hackathon.project.demoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ProfessionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProfessionServiceApplication.class, args);
	}

}
