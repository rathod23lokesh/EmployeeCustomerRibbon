package com.example.demo.controller;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class EmployeeConsumerRibbonController {
	@Autowired
	private LoadBalancerClient loadBalancer;
	
	public void getEmployee() throws RestClientException, IOException {		
		ServiceInstance serviceInstance=loadBalancer.choose("employee-producer");		
		System.out.println(serviceInstance.getUri());		
		String baseUrl=serviceInstance.getUri().toString();		
		baseUrl=baseUrl+"/Employee";		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response=null;
		try{
		response=restTemplate.exchange(baseUrl,
				HttpMethod.GET, getHeaders(),String.class);
		}catch (Exception ex)
		{
			System.out.println(ex);
		}
		System.out.println(response.getBody());
	}

	private static HttpEntity<?> getHeaders() throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		return new HttpEntity<>(headers);
	}
	
	@GetMapping("/")
	public void getdata() {
		ServiceInstance serviceInstance = loadBalancer.choose("employee-producer");
		String baseUrl = serviceInstance.getUri().toString();
		baseUrl = baseUrl + "/Employee";
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = null;
		try {
			response = restTemplate.exchange(baseUrl, HttpMethod.GET, getHeaders(), String.class);
		} catch (Exception ex) {
			System.out.println(ex);
		}
		System.out.println(response.getBody());

	}
	
	@GetMapping("/test")
	private String test()
	{
		return "This service is up!!";
	}
}
