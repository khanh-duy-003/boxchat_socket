package com.duypk.socket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import vn.com.unit.springframework.data.mirage.repository.config.EnableMirageRepositories;

@SpringBootApplication
public class BoxchatSocketApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoxchatSocketApplication.class, args);
	}

}
