package com.example.demo;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClientException;

import com.example.demo.controller.EmployeeConsumerRibbonController;

@SpringBootApplication
public class EmployeeConsumerRibbonApplication {

	public static void main(String[] args) throws RestClientException, IOException {
		ApplicationContext ctx = SpringApplication.run(
				EmployeeConsumerRibbonApplication.class, args);
		
		EmployeeConsumerRibbonController consumerControllerClient=ctx.getBean(EmployeeConsumerRibbonController.class);
		System.out.println(consumerControllerClient);
		consumerControllerClient.getEmployee();
		
	}
	
	@Bean
	public  EmployeeConsumerRibbonController  consumerControllerClient()
	{
		return  new EmployeeConsumerRibbonController();
	}

}
