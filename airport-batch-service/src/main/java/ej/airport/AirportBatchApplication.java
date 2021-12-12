package ej.airport;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableBatchProcessing
@EnableAsync
@EnableFeignClients
@SpringBootApplication
public class AirportBatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirportBatchApplication.class, args);
	}

}
