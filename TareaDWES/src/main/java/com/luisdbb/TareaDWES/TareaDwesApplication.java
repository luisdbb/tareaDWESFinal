package com.luisdbb.TareaDWES;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.luisdbb.TareaDWES.principal.Principal;

@SpringBootApplication
public class TareaDwesApplication {

	public static void main(String[] args) {
		SpringApplication.run(TareaDwesApplication.class, args);
		Principal.main(args);
	}

}
