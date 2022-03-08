package com.personal.taller;

import com.personal.taller.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@SpringBootApplication
//@ComponentScan({"com.delivery.request"})
//@EntityScan("com.delivery.domain")
@EnableJpaRepositories("com.personal.taller.repository")
public class TallerApplication {

	//@Autowired
	//ClienteRepository clienteRepository;

	public static void main(String[] args) {
		SpringApplication.run(TallerApplication.class, args);
	}

}
