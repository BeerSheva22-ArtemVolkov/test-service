package telran.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestServiceApplication {

	// сначала запустится Tomcat, потом возьмет аппликацию, соберет сервлеты и передаст их в работу томкату
	public static void main(String[] args) {
		SpringApplication.run(TestServiceApplication.class, args);
	}

}
