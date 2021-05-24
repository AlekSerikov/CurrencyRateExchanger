package com.example.demo;

import com.amazonaws.services.s3.AmazonS3;
import com.example.demo.service.CurrencyS3ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	@Autowired
	AmazonS3 amazonS3;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		System.out.println(amazonS3.listBuckets());

	}
}